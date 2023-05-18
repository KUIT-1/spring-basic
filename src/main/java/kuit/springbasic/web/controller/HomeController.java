package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.awt.desktop.QuitEvent;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final QuestionDao questionDao;
    @RequestMapping("/")
    public ModelAndView showHome() {
        log.info("HomeController.showHome");
        ModelAndView modelAndView = new ModelAndView("/home");
        List<Question> questions = questionDao.findAll();
        modelAndView.addObject("questions", questions);
        return modelAndView;
    }

}
