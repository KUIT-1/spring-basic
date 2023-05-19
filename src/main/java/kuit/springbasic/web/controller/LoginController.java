package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final UserDao userDao;

    @RequestMapping("/loginForm")
    public String forwardToUserLoginForm(){
        return "/user/login";
    }

    @RequestMapping("/loginFailed")
    public String forwardToUserLoginFailed(){
        return "/user/loginFailed";
    }

    //@RequestMapping("/login")
    public String loginV1(@RequestParam("userId") String userId, // 사용자가 request로 줄 때 사용한 이름과 선언된 이름이 같다면 ("userId), ("password") 생략 가능
                        @RequestParam("password") String password,
                        HttpServletRequest request) throws SQLException {
        log.info("loginV1");
        User loggedInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(loggedInUser)) {
            request.getSession().setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @RequestMapping("/login")
    public String loginV2(@ModelAttribute User loggedInUser, // 아무 필드가 선언되지 않은 기본 생성자 통해 객체 생성 후, @ModelAttribute 안의 parameter에 저장된 값들을 setter를 통해 그 생성된 객체에 넣는다.
                          HttpServletRequest request) throws SQLException {      // 따라서 Setter가 선언되어 있어야 함.
        log.info("loginV2");
        User user = userDao.findByUserId(loggedInUser.getUserId());
        if (user != null && user.equals(loggedInUser)) {
            request.getSession().setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @RequestMapping("/logout")
    public String logoutV1(HttpServletRequest request){
        log.info("logoutV1");
        request.getSession().removeAttribute(USER_SESSION_KEY);
        return "redirect:/";
    }
}
