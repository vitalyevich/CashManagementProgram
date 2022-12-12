package com.cashmanagement.vitalyevich.client.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Brigade extends ColorTable {

    private Integer id;

    private Company company;

    private String brigadeName;

    private Boolean active = false;

    private String brigadeActive;

    public String getBrigadeActive() {
        return active.equals(false) ? "разрешен" : "запрещен";
    }

    @Override
    public String getColorFirst() {
        return active.equals(false) ? "#57DB4E" : "#FF3F3F";
    }

    public void setBrigadeActive(String brigadeActive) {
        this.brigadeActive = brigadeActive;
    }

    private String brigadeUsers;

    public String getBrigadeUsers() {
        return brigadeUsers;
    }

    public void setBrigadeUsers(String brigadeUsers) {
        this.brigadeUsers = brigadeUsers;
    }

    private Set<User> users = new LinkedHashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getBrigadeName() {
        return brigadeName;
    }

    public void setBrigadeName(String brigadeName) {
        this.brigadeName = brigadeName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Brigade() {
    }

    public Brigade(Set<User> users) {
        this.users = users;
    }

    public Brigade(String brigadeName, Boolean active, Set<User> users) {
        this.brigadeName = brigadeName;
        this.active = active;
        this.users = users;
    }
}