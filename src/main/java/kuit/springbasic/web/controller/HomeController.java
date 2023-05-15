package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestionDao questionDAO = new QuestionDao();

    @RequestMapping("/")
    public String showHome(Model model) {
        log.info("HomeController.showHome");

        // 코드 추가 필요
        List<Question> questions = questionDAO.findAll();
        model.addAttribute("questions", questions);

        return "home";
    }

}
