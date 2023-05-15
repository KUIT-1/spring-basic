package kuit.springbasic.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ForwardController {

    @GetMapping("/user/form")
    public String userForm() {
        log.info("ForwardController.userForm");
        return "user/form";
    }

    @GetMapping("/user/loginForm")
    public String loginForm() {
        log.info("ForwardController.loginForm");
        return "user/login";
    }

    @GetMapping("/user/loginFailed")
    public String loginFailed() {
        log.info("ForwardController.loginFailed");
        return "user/loginFailed";
    }
}
