package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestionDao questionDao;

/*
    @RequestMapping("/")
    public ModelAndView showHomeV1(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        log.info("HomeController.showHome");

        ModelAndView modelAndView = new ModelAndView("/home");

        List<Question> questions = questionDao.findAll();
        modelAndView.getModel().put("questions", questions);
        modelAndView.addObject("questions");

        return modelAndView;
    }*/



    @GetMapping("/")
    public String showHomeV2(Model model) throws SQLException {
        log.info("HomeController.showHome");
        // 코드 추가 필요
        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions", questions);
        return "/home";
    }

}
