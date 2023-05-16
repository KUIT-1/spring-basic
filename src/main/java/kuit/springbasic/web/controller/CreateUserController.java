package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CreateUserController {
    private final UserDao userDao = new UserDao();

    @RequestMapping("/user/signup")
    public String execute(@ModelAttribute User user) throws Exception {
        userDao.insert(user);
        return "redirect:/user/list";
    }

}
