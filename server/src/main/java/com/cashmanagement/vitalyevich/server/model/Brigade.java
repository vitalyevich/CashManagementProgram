package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "brigades")
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brigade_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "brigade_name", nullable = false)
    private String brigadeName;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @ManyToMany
    @JoinTable(name = "brigade_users",
            joinColumns = @JoinColumn(name = "brigade_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
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

    public Brigade(Integer id) {
        this.id = id;
    }

    public Brigade() {
    }
}