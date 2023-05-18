package kuit.springbasic.web.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Getter
@Setter
public class Question {


    long questionId;
    String writer;
    String title;
    String contents;

    Date createdDate;
    int countOfAnswer;


    public Question(long questionId, String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(long questionId, String writer, String title, String contents, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = new Date();
        this.countOfAnswer = countOfAnswer;
    }


}
