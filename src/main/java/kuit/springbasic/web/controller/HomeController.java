package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestionDao questionDao;

//    @RequestMapping("/")
    public ModelAndView showHomeV1() {
        log.info("HomeController.showHome");

        ModelAndView modelAndView = new ModelAndView("/home");
        List<Question> questions = questionDao.findAll();
        modelAndView.addObject("questions", questions);

        return modelAndView;
    }

    @RequestMapping("/")
    public String showHomeV2(Model model) {
        log.info("HomeController.showHome");
        List<Question> questions = questionDao.findAll();

        model.addAttribute("questions", questions);
        return "/home";
    }

}
