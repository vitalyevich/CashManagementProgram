package com.cashmanagement.vitalyevich.client.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Country {

    private Integer id;

    private String countryName;

    private Integer countryCities;

    private Integer cityAtms;

    public Integer getCityAtms() {
        return cityAtms;
    }

    public void setCityAtms(Integer cityAtms) {
        this.cityAtms = cityAtms;
    }

    private String cityName;

    private String atmUid;

    public String getAtmUid() {
        return cities.iterator().next().getAtms().iterator().next().getAtmUid();
    }

    public void setAtmUid(String atmUid) {
        this.atmUid = atmUid;
    }

    public String getCityName() {
        if (cities == null || !cities.iterator().hasNext()) {
            return " ";
        }
        return cities.iterator().next().getCityName();
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCountryCities() {
        return countryCities;
    }

    public void setCountryCities(Integer countryCities) {
        this.countryCities = countryCities;
    }

    private Set<City> cities = new LinkedHashSet<>();

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}