package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UpdateUserController {

    private final UserDao userDao = new UserDao();

    @RequestMapping("/user/update")
    public String execute(@ModelAttribute User user) throws Exception {
        userDao.update(new User(
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()));

        return "redirect:/user/list";
    }

}