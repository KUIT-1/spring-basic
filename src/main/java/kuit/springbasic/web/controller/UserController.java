package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import kuit.springbasic.web.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao = new UserDao();

    @RequestMapping("/form")
    public String forwardToUserForm(){
        return "/user/form";
    }

    @RequestMapping("/signup")
    public String userSignUp(@ModelAttribute User loggedInUser){
        userDao.insert(loggedInUser);
        return "redirect:/user/list";
    }

    @RequestMapping("/updateForm")
    public String userUpdateForm(@ModelAttribute User loggedInUser, Model model) throws SQLException {
        log.info("userUpdateForm");
        String userId = loggedInUser.getUserId();
        User user = userDao.findByUserId(userId);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/updateForm";
        }
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String userUpdate(@ModelAttribute User loggedInUser){
        log.info("userUpdate");
        userDao.update(loggedInUser);
        return "redirect:/user/list";
    }

    @RequestMapping("/list")
    public String userList(Model model, HttpServletRequest request){
        log.info("userList");
        if (UserSessionUtils.isLoggedIn(request.getSession())) {
            model.addAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }

}
