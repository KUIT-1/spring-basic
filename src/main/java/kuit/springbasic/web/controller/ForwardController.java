package kuit.springbasic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {
    @RequestMapping("/user/loginForm")
    public String forwardToUserLoginForm(){
        return "/user/login";
    }
    @RequestMapping("user/loginFailed")
    public String forwardToUserLoginFailed(){
        return "/user/loginFailed";
    }
}
