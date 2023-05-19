package ru.yandex.praktikum.model.api;

public class UserCredentials {
    private String email;
    private String password;
    private String name;

    public UserCredentials(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name=name;
    }

    public static UserCredentials from (User user) {
        return new UserCredentials(user.getEmail(), user.getPassword(), user.getName());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName(){return name;}
    public void setName(String name) {this.name=name;}

    @Override
    public String toString() {
        return "UserCredentials{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
