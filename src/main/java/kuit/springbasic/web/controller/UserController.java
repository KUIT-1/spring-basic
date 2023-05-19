package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import kuit.springbasic.web.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

//    controllers.put("/v1/user/form", new ForwardControllerV1("/v1/user/form"));
//    controllers.put("/v1/user/signup", new CreateUserControllerV1());
//    controllers.put("/v1/user/updateForm", new UpdateUserFormControllerV1());
//    controllers.put("/v1/user/update", new UpdateUserControllerV1());
//    controllers.put("/v1/user/list", new UserListControllerV1());

    @RequestMapping("/form")
    public String showUserForm(){
        log.info("UserController.showUserForm");
        return "/user/form";
    }

    @RequestMapping("/signup")
    public String createUser(@ModelAttribute User user) {
        log.info("UserController.createUser");
        userDao.insert(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/list")
    public String showUserList(HttpServletRequest request, Model model) {
        log.info("UserController.showUserList");
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLoggedIn(session)) {
            model.addAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }

    @RequestMapping("/updateForm")
    public String showUserUpdateForm(@RequestParam String userId, Model model) throws SQLException {
        log.info("UserController.showUserUpdateForm");

        User user = userDao.findByUserId(userId);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/updateForm";
        }
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateUserV2(@ModelAttribute User user) {
        log.info("UserController.updateUserV2");
        userDao.update(user);
        return "redirect:/user/list";
    }



}
