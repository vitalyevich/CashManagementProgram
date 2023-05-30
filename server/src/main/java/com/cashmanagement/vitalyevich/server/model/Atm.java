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

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "home_num", nullable = false)
    private Integer homeNum;

    @ManyToMany
    @JoinTable(name = "city_atms",
            joinColumns = @JoinColumn(name = "atm_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private Set<City> cities = new LinkedHashSet<>();

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

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Integer getHomeNum() {
        return homeNum;
    }

    public void setHomeNum(Integer homeNum) {
        this.homeNum = homeNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Atm() {
    }

    public Atm(Integer id, String atmUid, String cashState, String atmState, String address, Integer homeNum, Set<City> cities, Set<Cassette> cassettes, Set<Company> companies) {
        this.id = id;
        this.atmUid = atmUid;
        this.cashState = cashState;
        this.atmState = atmState;
        this.address = address;
        this.homeNum = homeNum;
        this.cities = cities;
        this.cassettes = cassettes;
        this.companies = companies;
    }

    public Atm(String atmUid, String cashState, String atmState, String address, Integer homeNum, Set<City> cities, Set<Cassette> cassettes, Set<Company> companies) {
        this.atmUid = atmUid;
        this.cashState = cashState;
        this.atmState = atmState;
        this.address = address;
        this.homeNum = homeNum;
        this.cities = cities;
        this.cassettes = cassettes;
        this.companies = companies;
    }
}