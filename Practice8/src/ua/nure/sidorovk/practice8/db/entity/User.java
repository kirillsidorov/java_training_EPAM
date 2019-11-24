package ua.nure.sidorovk.practice8.db.entity;

import java.util.Objects;

public class User {
    private String login;
    private int id;

    private User(String name) {
        this.login = name;
    }

    public static User createUser(String name) {
        return new User(name);
    }

    public String getLogin(){
        return this.login;
    }

    @Override
    public String toString() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return this.login.equals(user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
