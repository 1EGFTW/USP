package bg.tuvarna.sit.usp_cars.presentation.models;

import java.util.Objects;

public class UserModel {
    private String user_username;
    private String user_password;
    private Boolean is_admin;

    public UserModel() {
    }

    public UserModel(String user_username, String user_password) {
        this.user_username = user_username;
        this.user_password = user_password;
    }

    public UserModel(String user_username, String user_password, Boolean is_admin) {
        this.user_username = user_username;
        this.user_password = user_password;
        this.is_admin = is_admin;
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
        UserModel userModel = (UserModel) o;
        return Objects.equals(user_username, userModel.user_username) && Objects.equals(user_password, userModel.user_password) && Objects.equals(is_admin, userModel.is_admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_username, user_password, is_admin);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_username='" + user_username + '\'' +
                ", user_password='" + user_password + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }
}
