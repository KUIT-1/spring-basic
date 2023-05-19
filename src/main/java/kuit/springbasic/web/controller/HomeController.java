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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final QuestionDao questionDAO;

//    @Autowired
//    public HomeController(QuestionDao questionDAO) {
//        this.questionDAO = questionDAO;
//    }
    //@RequiredArgsConstructor 가 있으면 생략 가능

    // V1. ViewName 반환
    @RequestMapping("/")   // @ 기반 Controller 하나가 됨
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    //@GetMapping("/")  // "/" url로 GET방식의 request가 온다면 이 Controller를 실행시켜 해당 url에 대한 reponse를 준다.
    public String showHomeV1(Model model) {
        log.info("HomeController.showHome");

        List<Question> questions = questionDAO.findAll();
        model.addAttribute("questions", questions);

        return "home";
    }

    // V2. ModelAndView 반환
    //@RequestMapping("/")
    public ModelAndView showHomeV2() {
        log.info("HomeController.showHome");

        ModelAndView modelAndView = new ModelAndView("/home");

        List<Question> questions = questionDAO.findAll();
        //modelAndView.getModel().put("questions", questions);
        modelAndView.addObject("questions", questions);

        return modelAndView;
    }

}
