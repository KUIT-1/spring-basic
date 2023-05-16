package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UpdateUserFormController {

    private final UserDao userDao = new UserDao();

    @RequestMapping("/user/updateForm")
    public String execute(@ModelAttribute User loginUser) throws Exception {
        User user = userDao.findByUserId(loginUser.getUserId());

        if (user != null) {
            return "/user/updateForm";
        }

        return "redirect:/";
    }

}
