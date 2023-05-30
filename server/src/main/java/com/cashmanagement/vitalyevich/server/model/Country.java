package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private Integer id;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @ManyToMany
    @JoinTable(name = "country_cities",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
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

    public Country() {
    }

    public Country(Integer id, String countryName, Set<City> cities) {
        this.id = id;
        this.countryName = countryName;
        this.cities = cities;
    }

    public Country(String countryName, Set<City> cities) {
        this.countryName = countryName;
        this.cities = cities;
    }
}