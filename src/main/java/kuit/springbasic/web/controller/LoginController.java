package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final UserDao userDao;

    @RequestMapping("/loginForm")
    public String forwardToUserLoginForm(){
        return "/user/login";
    }

    @RequestMapping("/loginFailed")
    public String forwardToUserLoginFailed(){
        return "/user/loginFailed";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute User loggedInuser, HttpServletRequest request) throws SQLException {
        log.info("LoginController.login");

        User user = userDao.findByUserId(loggedInuser.getUserId());

        if(user!=null && user.equals(loggedInuser)){
            log.info(loggedInuser.toString());
            request.getSession().setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        log.info("LoginController.logout");

        request.getSession().removeAttribute("user");
        return "redirect:/";
    }


}
