package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import kuit.springbasic.web.util.UserSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping({"/user"})
@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserDao userDao;

    @RequestMapping({"/form"})
    public String ForwardController() {
        return "/user/form";
    }

    @RequestMapping({"/signup"})
    public String CreateUserController(@ModelAttribute User user, HttpServletRequest request) throws SQLException {
        this.userDao.insert(user);
        return "redirect:/user/list";
    }

    @RequestMapping({"/updateForm"})
    public String UpdateUserFormController(@RequestParam String userId, HttpServletRequest request) throws SQLException {
        User user = this.userDao.findByUserId(userId);
        if (user != null) {
            request.setAttribute("user", user);
            return "/user/updateForm";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping({"/update"})
    public String UpdateUserController(@ModelAttribute User user, HttpServletRequest request) {
        this.userDao.update(user);
        return "redirect:/user/list";
    }

    @RequestMapping({"/list"})
    public String ListUserController(@ModelAttribute String userId, HttpServletRequest request) throws SQLException {
        if (UserSessionUtils.isLoggedIn(request.getSession())) {
            request.setAttribute("users", this.userDao.findAll());
            return "/user/list";
        } else {
            return "redirect:/user/loginForm";
        }
    }

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }
}

