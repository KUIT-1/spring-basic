package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    TODO: LogIn, LogOut
 */

@Controller
@Slf4j
@RequiredArgsConstructor
public class LogInController {
    private final UserDao userDao;

    @RequestMapping("/user/loginForm")
    public String forwardToUserLoginForm(){
        log.info("LogInController.forwardToUserLoginForm");
        return "/user/login";
    }
    @RequestMapping("/user/loginFailed")
    public String forwardToUserLoginFailed(){
        log.info("LogInController.forwardToUserLoginFailed");
        return "/user/loginFailed";
    }
}
