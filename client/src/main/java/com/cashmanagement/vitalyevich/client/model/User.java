package com.cashmanagement.vitalyevich.client.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private Set<Company> companies = new LinkedHashSet<>();

    private Set<Role> roles = new LinkedHashSet<>();

    public String getRoles() {
        return roles.iterator().next().getRoleName();
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getCompanies() {
        return companies.iterator().next().getCompanyName();
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}