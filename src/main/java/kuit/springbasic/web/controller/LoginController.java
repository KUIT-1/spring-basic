package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final UserDao userDao;

    @RequestMapping("/loginForm")
    public String forwardToUserLoginForm(){
        log.info("LoginController.forwardToUserLoginForm");
        return "user/login";
    }

    @RequestMapping("/loginFailed")
    public String forwardToUserLoginFailed(){
        log.info("LoginController.forwardToUserLoginFailed");
        return "/user/loginFailed";
    }

    //@RequestMapping("/login")
    public String loginV1(@RequestParam("userId") String userId, @RequestParam("password") String password,
                        HttpServletRequest request) throws SQLException {

        User loggedInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(loggedInUser)) {
            request.getSession().setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    public String loginV2(@ModelAttribute User loggedInUser, HttpServletRequest request) throws SQLException {
        User user = userDao.findByUserId(loggedInUser.getUserId());

        if (user != null && user.equals(loggedInUser)) {
            request.getSession().setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }
}
