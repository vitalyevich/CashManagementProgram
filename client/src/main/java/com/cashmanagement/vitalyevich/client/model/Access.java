package com.cashmanagement.vitalyevich.client.model;

public class Access extends ColorTable {

    private Integer id;

    private User user;

    private String login;

    private String userPassword;

    private Boolean active = false;

    private String stage;

    @Override
    public String getColorFirst() {
        return active.equals(true) ? "#57DB4E" : "#FF3F3F";
    }

    public Boolean getActive() {
        return active;
    }

    public String getStage() {
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