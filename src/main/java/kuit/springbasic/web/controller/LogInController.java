package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

/*
    Done: showForm, LogIn, LogOut
 */

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class LogInController {
    private final UserDao userDao;

    @RequestMapping("/loginForm")
    public String showLogInForm(){
        log.info("LogInController.showLogInForm");
        return "/user/login";
    }
    @RequestMapping("/loginFailed")
    public String forwardToUserLoginFailed(){
        log.info("LogInController.forwardToUserLoginFailed");
        return "/user/loginFailed";
    }

    @RequestMapping("/login")
    public String logIn(@RequestParam String userId, @RequestParam String password, HttpServletRequest request) throws SQLException {
        log.info("LogInController.login");
        User loggedInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if(user!=null && user.equals(loggedInUser)){
            HttpSession session = request.getSession();
            session.setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        log.info("LogInController.logout");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/";
    }

}
