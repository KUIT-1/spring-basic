package kuit.springbasic.web.dao;

import java.sql.SQLException;
import java.util.List;
import kuit.springbasic.jdbc.JdbcTemplate;
import kuit.springbasic.web.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

    public UserDao() {
    }

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        this.jdbcTemplate.update(sql, (pstmt) -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    public void update(User user) {
        String sql = "UPDATE USERS set password=?, name=?, email=? WHERE userId=?";
        this.jdbcTemplate.update(sql, (pstmt) -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        });
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return this.jdbcTemplate.query(sql, (rs) -> {
            return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
        });
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
        return (User)this.jdbcTemplate.queryForObject(sql, (pstmt) -> {
            pstmt.setString(1, userId);
        }, (rs) -> {
            return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
        });
    }
}

