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
import org.springframework.web.servlet.HttpServletBean;

import java.net.http.HttpRequest;

/*
    TODO: updateForm, update,
    Done: create(signup), showForm, list
 */

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    @RequestMapping("/form")
    public String showUserForm(){
        log.info("UserController.showUserForm");
        return "/user/form";
    }
    @RequestMapping("/signup")
    public String createUser(@ModelAttribute User user){
        log.info("UserController.createUser");
        userDao.insert(user);
        return "redirect:/";
    }

    @RequestMapping("/list")
    public String showUserList(Model model, HttpServletRequest request){
        log.info("UserController.showUserList");
        HttpSession session = request.getSession();
        if(UserSessionUtils.isLoggedIn(session)){
            // login 상태면
            model.addAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }

}
