package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpSession;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final UserDao userDao = new UserDao();

    @PostMapping("/user/login")
    public String login(@RequestParam String userId, @RequestParam String password, HttpSession session) throws SQLException {
        log.info("LoginController.login");

        User loggedInUser = new User(userId, password, null, null);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(loggedInUser)) {
            session.setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        log.info("LoginController.logout");
        session.removeAttribute("user");
        return "redirect:/";
    }

}
