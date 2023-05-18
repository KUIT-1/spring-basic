package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final QuestionDao questionDao;

    @RequestMapping({"/"})
    public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        log.info("HomeController.showHome");
        ModelAndView modelAndView = new ModelAndView("/home");
        List<Question> questions = this.questionDao.findAll();
        modelAndView.addObject("questions", questions);
        return modelAndView;
    }

    public HomeController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }
}
