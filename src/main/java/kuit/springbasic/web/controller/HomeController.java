package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final QuestionDao questionDao;

    //@RequestMapping(value="/", method= RequestMethod.GET)
    //@GetMapping("/")
    public ModelAndView showHomeV1(Model model) {
        log.info("HomeController.showHome");

        // 코드 추가 필요
        ModelAndView modelAndView = new ModelAndView("/home");
        List<Question> questions = questionDao.findAll();
        modelAndView.getModel().put("questions",questions);

        return modelAndView;
    }
    @GetMapping("/")
    public String showHomeV2(Model model){
        log.info("HomeController.showHome");

        // 코드 추가 필요
        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions",questions);

        return "/home";
    }

}
