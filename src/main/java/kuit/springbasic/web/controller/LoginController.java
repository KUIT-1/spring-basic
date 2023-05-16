package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserDao userDao = new UserDao();

    @RequestMapping("/user/login")
    public String execute(@ModelAttribute User loginUser, HttpServletRequest request) throws Exception {
        User user = userDao.findByUserId(loginUser.getUserId());

        if (user != null && user.equals(loginUser)) {
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }

}
