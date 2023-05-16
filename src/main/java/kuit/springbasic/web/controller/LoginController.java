package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {
    private final UserDao userDao = new UserDao();

    @RequestMapping("/login")
    public String login(@ModelAttribute User loginUser, Model model) throws Exception {
        log.info("LoginController.login");
        User user = userDao.findByUserId(loginUser.getUserId());

        if (user != null && user.equals(loginUser)) {
            model.addAttribute("user", user);
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("LoginController.logout");
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }

}
