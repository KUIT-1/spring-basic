package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
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
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    @RequestMapping("/form")
    public String forwardToUserForm(){
        return "/user/form";
    }

    @RequestMapping("/signup")
    public String createUser(@ModelAttribute User user){
        userDao.insert(user);

        return "redirect:/user/list";
    }

    @RequestMapping("/updateForm")
    public String updateUserForm(@RequestParam String userId, Model model) throws SQLException {
        log.info("UserController.updateUserForm: "+userId);

        User user = userDao.findByUserId(userId);

        if(user!=null){
            model.addAttribute("user",user);
            return "/user/updateForm";
        }
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateUser(@ModelAttribute User user){
        log.info("UserController.updateUser");

        userDao.update(user);

        return "redirect:/user/list";
    }

    @RequestMapping("/list")
    public String showUserList(HttpServletRequest request, Model model){
        log.info("UserController.showUserList");

        if(UserSessionUtils.isLoggedIn(request.getSession())){
            model.addAttribute("users",userDao.findAll());
            return "/user/list";
        }

        return "redirect:/user/loginForm";


    }


}
