package com.cashmanagement.vitalyevich.client.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @Column(name = "phone", nullable = false, length = 19)
    private String phone;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    private String roleName;

    private String nameRole;

    public String getNameRole() {
        String role = roles.iterator().next().getRoleName();
        if (role.equals("ROLE_ADMIN")) {
            return "Руководитель";
        }
        else if (role.equals("ROLE_DIALER")) {
            return "Старший дилер";
        }
        else if (role.equals("ROLE_CASHIER")) {
            return "Старший кассир";
        }
        else if (role.equals("ROLE_CASHIERSTORAGE")) {
            return "Старший кассир хранилища";
        }
        else {
            return "Старший инкассатор";
        }

    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    private String companyName;

    @ManyToMany
    @JoinTable(name = "company_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> companies = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public String getRoleName() {
        return roles.iterator().next().getRoleName();
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public String getCompanyName() {
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

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String firstName, String lastName, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}