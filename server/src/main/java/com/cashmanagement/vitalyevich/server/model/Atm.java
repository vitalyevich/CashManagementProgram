package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "atms", indexes = {
        @Index(name = "atms_atm_uid_key", columnList = "atm_uid", unique = true)
})
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atm_id", nullable = false)
    private Integer id;

    @Column(name = "atm_uid", nullable = false, length = 8)
    private String atmUid;

    @Column(name = "cash_state", nullable = false)
    private String cashState;

    @Column(name = "atm_state", nullable = false)
    private String atmState;

    @ManyToMany
    @JoinTable(name = "atm_cassettes",
            joinColumns = @JoinColumn(name = "atm_id"),
            inverseJoinColumns = @JoinColumn(name = "cassette_id"))
    private Set<Cassette> cassettes = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "company_atms",
            joinColumns = @JoinColumn(name = "atm_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> companies = new LinkedHashSet<>();

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Set<Cassette> getCassettes() {
        return cassettes;
    }

    public void setCassettes(Set<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    public String getAtmState() {
        return atmState;
    }

    public void setAtmState(String atmState) {
        this.atmState = atmState;
    }

    public String getCashState() {
        return cashState;
    }

    public void setCashState(String cashState) {
        this.cashState = cashState;
    }

    public String getAtmUid() {
        return atmUid;
    }

    public void setAtmUid(String atmUid) {
        this.atmUid = atmUid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}