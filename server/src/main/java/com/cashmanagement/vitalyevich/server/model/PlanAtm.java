package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "plan_atms")
public class PlanAtm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "atm_id", nullable = false)
    private Atm atm;

    @Column(name = "plan_method", nullable = false, length = 30)
    private String planMethod;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "plan_period", nullable = false)
    private Integer planPeriod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "plan_cassettes",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "cassette_id"))
    private Set<Cassette> cassettes = new LinkedHashSet<>();

    public Set<Cassette> getCassettes() {
        return cassettes;
    }

    public void setCassettes(Set<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPlanPeriod() {
        return planPeriod;
    }

    public void setPlanPeriod(Integer planPeriod) {
        this.planPeriod = planPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlanMethod() {
        return planMethod;
    }

    public void setPlanMethod(String planMethod) {
        this.planMethod = planMethod;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlanAtm(Integer id, Atm atm, String planMethod, String status, Integer planPeriod, User user, Set<Cassette> cassettes) {
        this.id = id;
        this.atm = atm;
        this.planMethod = planMethod;
        this.status = status;
        this.planPeriod = planPeriod;
        this.user = user;
        this.cassettes = cassettes;
    }

    public PlanAtm(Atm atm, String planMethod, String status, Integer planPeriod, User user, Set<Cassette> cassettes) {
        this.atm = atm;
        this.planMethod = planMethod;
        this.status = status;
        this.planPeriod = planPeriod;
        this.user = user;
        this.cassettes = cassettes;
    }

    public PlanAtm() {
    }
}