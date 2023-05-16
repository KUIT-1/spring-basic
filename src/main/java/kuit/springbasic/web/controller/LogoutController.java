package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LogoutController {

    @RequestMapping("/user/logout")
    public String execute(HttpServletRequest request) throws Exception {
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }

}
