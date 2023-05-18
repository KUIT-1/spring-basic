package kuit.springbasic.web.dao;

import java.sql.SQLException;
import java.util.List;
import kuit.springbasic.jdbc.JdbcTemplate;
import kuit.springbasic.jdbc.KeyHolder;
import kuit.springbasic.web.domain.Question;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDao {
    JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public QuestionDao() {
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        return this.jdbcTemplate.query(sql, (rs) -> {
            return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
        });
    }

    public Question insert(Question question) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();
        String sql = "INSERT INTO QUESTIONS (WRITER, TITLE, CONTENTS, CREATEDDATE, COUNTOFANSWER) VALUES (?,?,?,?,?)";
        this.jdbcTemplate.update(sql, (pstmt) -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setObject(4, question.getCreatedDate());
        }, keyHolder);
        return this.findByQuestionId(keyHolder.getId());
    }

    public Question findByQuestionId(int questionId) throws SQLException {
        return (Question)this.jdbcTemplate.queryForObject("SELECT questionId, writer, title,contents, createdDate,countOfAnswer FROM QUESTIONS WHERE questionID=?", (pstmt) -> {
            pstmt.setObject(1, questionId);
        }, (rs) -> {
            return new Question((long)rs.getInt("questionId"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
        });
    }
}
