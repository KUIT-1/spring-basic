package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final QuestionDao questionDao;
    @RequestMapping("/")
    public String showHome(Model model) {
        log.info("HomeController.showHomeV2");
        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions", questions);
        return "home";
    }

}
