package kuit.springbasic.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String showHome(Model model) {
        log.info("HomeController.showHome");

        // 코드 추가 필요

        return "home";
    }

}
