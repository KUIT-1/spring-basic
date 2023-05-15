package kuit.springbasic.web.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = {"userId", "password"})
@AllArgsConstructor
public class User {

    private String userId;

    private String password;

    private String name;

    private String email;

//    public User(String userId, String password) {
//        this.userId = userId;
//        this.password = password;
//    }

    public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.email = updateUser.email;
    }

}
