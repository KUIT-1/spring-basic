package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UserListController {

    private final UserDao userDao = new UserDao();

    @RequestMapping("/user/list")
    public String execute(HttpServletRequest request) throws Exception {
        if (UserSessionUtils.isLoggedIn(request.getSession())) {
            request.getSession().setAttribute("users", userDao.findAll());
            return "/user/list";
        }

        return "redirect:/user/loginForm";
    }

}

