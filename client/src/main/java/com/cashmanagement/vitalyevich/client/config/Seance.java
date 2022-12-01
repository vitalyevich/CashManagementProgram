package com.cashmanagement.vitalyevich.client.config;

import com.cashmanagement.vitalyevich.client.model.User;

public class Seance {

    public static void setInstance(Seance instance) {
        Seance.instance = instance;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private static Seance instance;

    public static Seance getInstance() {
        if (instance == null) {
            instance = new Seance();
        }
        return instance;
    }

}