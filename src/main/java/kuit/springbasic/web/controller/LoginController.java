package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {

    private final UserDao userDao;

    @RequestMapping("/loginForm")
    public String forwardToUserLoginForm(){
        log.info("LoginController.forwardToUserLoginForm");
        return "/user/login";
    }

    @RequestMapping("/loginFailed")
    public String forwardToUserLoginFailed(){
        log.info("LoginController.forwardToUserLoginFailed");
        return "/user/loginFailed";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute User loggedInUser, HttpServletRequest request) throws SQLException {
        log.info("LoginController.login");

        User user = userDao.findByUserId(loggedInUser.getUserId());

        if (user != null && user.equals(loggedInUser)) {
            request.getSession().setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/loginFailed";
    }

    @RequestMapping("/logout")
    public String logout(@ModelAttribute User loggedInUser, HttpServletRequest request) throws SQLException {
        log.info("LoginController.logout");
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
