package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kuit.springbasic.web.util.UserSessionUtils;

import java.sql.SQLException;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDao userDao;


    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String SignUpForm() {

        log.info("UserController.showForm");

        return "user/form";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user) throws SQLException {

        log.info("UserController.createUser");

        if(userDao.findByUserId(user.getUserId()) != null) return "redirect:/user/form";

        userDao.insert(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/updateForm", method = RequestMethod.GET)
    public String updateForm(@RequestParam String userId, Model model) throws SQLException {
        User user = userDao.findByUserId(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute User user) throws SQLException {
        userDao.update(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/list", method =  RequestMethod.GET)
    public String updateList(Model model, HttpServletRequest req) throws SQLException {
        if (UserSessionUtils.isLoggedIn(req.getSession())) {
            model.addAttribute("users", userDao.findAll());
            return "user/list";
        }
        return "redirect:/user/loginForm";
    }


}
