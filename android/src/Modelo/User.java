package Modelo;

/**
 * Created by fauricio on 29/05/18.
 */

public class User {
    private String nickname;
    private String email;

    public User(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
