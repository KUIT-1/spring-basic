package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import kuit.springbasic.web.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao = new UserDao();

    @RequestMapping("/signup")
    public String createUser(@ModelAttribute User user) {
        log.info("UserController.createUser");
        userDao.insert(user);

        return "redirect:/user/list";
    }

    @RequestMapping("/updateForm")
    public String updateForm(@ModelAttribute User loginUser) throws Exception {
        log.info("UserController.updateForm");
        User user = userDao.findByUserId(loginUser.getUserId());

        if (user != null) {
            return "/user/updateForm";
        }

        return "redirect:/";
    }

    @RequestMapping("/update")
    public String update(@ModelAttribute User user) {
        log.info("UserController.update");

        userDao.update(new User(
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()));

        return "redirect:/user/list";
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        log.info("UserController.list");

        if (UserSessionUtils.isLoggedIn(request.getSession())) {
            request.getSession().setAttribute("users", userDao.findAll());
            return "/user/list";
        }

        return "redirect:/user/loginForm";
    }


}
