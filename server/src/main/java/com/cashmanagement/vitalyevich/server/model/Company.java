package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "companies", indexes = {
        @Index(name = "companies_company_name_key", columnList = "company_name", unique = true)
})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", nullable = false)
    private Integer id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "home_num", nullable = false)
    private Integer homeNum;

    @ManyToMany
    @JoinTable(name = "city_companies",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private Set<City> cities = new LinkedHashSet<>();

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company(Integer id, String companyName, String address, Integer homeNum, Set<City> cities) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.homeNum = homeNum;
        this.cities = cities;
    }

    public Company(String companyName, String address, Integer homeNum, Set<City> cities) {
        this.companyName = companyName;
        this.address = address;
        this.homeNum = homeNum;
        this.cities = cities;
    }

    public Company() {
    }
}