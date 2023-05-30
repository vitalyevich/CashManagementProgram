package com.cashmanagement.vitalyevich.client.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class City {

    private Integer id;

    private String cityName;

    private Integer cityAtms;

    private List<Atm> atmsList;

    public List<Atm> getAtmsList() {
        return atmsList;
    }

    public void setAtmsList(List<Atm> atmsList) {
        this.atmsList = atmsList;
    }

    public Integer getCityAtms() {
        return cityAtms;
    }

    public void setCityAtms(Integer cityAtms) {
        this.cityAtms = cityAtms;
    }

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
}