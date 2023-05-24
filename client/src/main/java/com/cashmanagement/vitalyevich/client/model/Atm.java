package com.cashmanagement.vitalyevich.client.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Atm extends ColorTable {

    private Integer id;

    private String atmUid;

    private String cashState;

    private String atmState;

    private String listCassettes;

    private String currency;

    private Integer day;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    private Integer amount;

    private String address;

    private Integer homeNum;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        if (!cassettes.iterator().hasNext()) {
            return " ";
        }
        return cassettes.iterator().next().getCurrency();
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getListCassettes() {
        return listCassettes;
    }

    public void setListCassettes(String listCassettes) {
        this.listCassettes = listCassettes;
    }

    private String company;

    public String getCompany() {
        if (!companies.iterator().hasNext()) {
            return " ";
        }
        return companies.iterator().next().getCompanyName();
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHomeNum() {
        return homeNum;
    }

    public void setHomeNum(Integer homeNum) {
        this.homeNum = homeNum;
    }

    private Set<Cassette> cassettes = new LinkedHashSet<>();

    private Set<Company> companies = new LinkedHashSet<>();

    private Set<City> cities = new LinkedHashSet<>();

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public String getColorFirst() {
        if (cashState == null) {
            return " ";
        }
        return cashState.equals("Нормальное") ? "#57DB4E" : "#FF3F3F";
    }

    @Override
    public String getColorSecond() {

        if (atmState == null) {
            return " ";
        }

        if (atmState.equals("Нормальный") || day > 20) {
            return "#57DB4E";
        } else if (atmState.equals("Неопределённый") || day < 20 && day > 10) {
            return "#F1CB00";
        } else {
            return "#FF3F3F";
        }
    }

    public String getCompanies() {
        if (!companies.iterator().hasNext()) {
            return " ";
        }
        return companies.iterator().next().getCompanyName();
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