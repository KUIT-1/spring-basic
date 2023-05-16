package kuit.springbasic.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ForwardController {

    @RequestMapping("/form")
    public String forwardToUserForm() {
        return "/user/form";
    }

    @RequestMapping("/loginForm")
    public String forwardToLoginForm() {
        return "/user/login";
    }

    @RequestMapping("/loginFailed")
    public String forwardToLoginFailed() {
        return "/user/loginFailed";
    }

}