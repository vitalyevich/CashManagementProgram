package com.cashmanagement.vitalyevich.client.model;


public class Access extends ColorTable {

    private Integer id;

    private User user;

    private String login;

    private String userPassword;

    private Boolean active = false;

    @Override
    public String getColor() {
        return active.equals(false) ? "#45A73E" : "#FF3F3F";
    }

    public String getActive() {
        return active.equals(false) ? "разрешен" : "запрещен";
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}