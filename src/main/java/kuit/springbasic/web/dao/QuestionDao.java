package kuit.springbasic.web.dao;


import kuit.springbasic.jdbc.JdbcTemplate;
import kuit.springbasic.web.domain.Question;

import java.sql.SQLException;
import java.util.List;
import kuit.springbasic.jdbc.KeyHolder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDao {
    private JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        return jdbcTemplate.query(sql,rs -> new Question(
                    rs.getLong("questionId"),
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getDate("createdDate"),
                    rs.getInt("countOfAnswer")));
    }
    public Question findByQuestionId(long questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE QUESTIONID = ?";

        return jdbcTemplate.queryForObject(sql,pstmt -> {
                pstmt.setLong(1,questionId);
                }, rs -> new Question(
                rs.getLong("questionid"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer")));
    }

    public void update(Question question) {
        String sql = "update questions set title=?, contents=? where questionId = ?";

        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContents());
            pstmt.setLong(3, question.getQuestionId());
        }, null);
    }

    public Question insert(Question question) throws SQLException {
            String sql = "INSERT INTO QUESTIONS (WRITER, TITLE, CONTENTS, CREATEDDATE, COUNTOFANSWER) " +
                    "VALUES (?, ?, ?, ?, ?)";
            KeyHolder holder = new KeyHolder();
            jdbcTemplate.update(sql,pstmt -> {
                pstmt.setString(1, question.getWriter());
                pstmt.setString(2, question.getTitle());
                pstmt.setString(3, question.getContents());
                pstmt.setObject(4, question.getCreatedDate());
                pstmt.setInt(5, question.getCountOfAnswer());
            }, holder);

            return findByQuestionId(holder.getId());
    }


}
