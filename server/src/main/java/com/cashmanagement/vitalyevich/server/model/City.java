package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Integer id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @ManyToMany
    @JoinTable(name = "city_atms",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "atm_id"))
    private Set<Atm> atms = new LinkedHashSet<>();

    public Set<Atm> getAtms() {
        return atms;
    }

    public void setAtms(Set<Atm> atms) {
        this.atms = atms;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City() {
    }

    public City(Integer id) {
        this.id = id;
    }

    public City(String cityName, Set<Atm> atms) {
        this.cityName = cityName;
        this.atms = atms;
    }

    public City(Integer id, String cityName, Set<Atm> atms) {
        this.id = id;
        this.cityName = cityName;
        this.atms = atms;
    }
}