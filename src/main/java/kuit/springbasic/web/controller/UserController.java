package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpSession;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import kuit.springbasic.web.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDao userDao;

    @GetMapping("/list")
    public String userList(Model model, HttpSession session) {
        log.info("UserController.userList");

        if (UserSessionUtils.isLoggedIn(session)) {
            model.addAttribute("users", userDao.findAll());
            return "user/list";
        }
        return "redirect:/user/loginForm";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam String userId, Model model) throws SQLException {
        log.info("UserController.updateForm");

        User user = userDao.findByUserId(userId);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/updateForm";
        }
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        log.info("UserController.signup");

        userDao.insert(user);

        return "redirect:/user/list";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user) {
        log.info("UserController.update");

        userDao.update(user);

        return "redirect:/user/list";
    }


}
