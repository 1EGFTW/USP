package bg.tuvarna.sit.usp_cars.data.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user",nullable = false)
    private int id_user;

    @Column(name = "user_username")
    private String user_username;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "is_admin")
    private Boolean is_admin;

    public User() {
    }

    public User(String user_username, String user_password, Boolean is_admin) {
        this.user_username = user_username;
        this.user_password = user_password;
        this.is_admin = is_admin;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user_username, user.user_username) && Objects.equals(user_password, user.user_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_username, user_password);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_username='" + user_username + '\'' +
                ", user_password='" + user_password + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }
}
