package com.cashmanagement.vitalyevich.client.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Atm extends ColorTable {

    private Integer id;

    private String atmUid;

    private String cashState;

    private String atmState;

    private String listCassettes;

    public String getListCassettes() {
        return listCassettes;
    }

    public void setListCassettes(String listCassettes) {
        this.listCassettes = listCassettes;
    }

    private Set<Cassette> cassettes = new LinkedHashSet<>();

    private Set<Company> companies = new LinkedHashSet<>();

    @Override
    public String getColorFirst() {
        return cashState.equals("Нормальное") ? "#57DB4E" : "#FF3F3F";
    }

    @Override
    public String getColorSecond() {

        if (atmState.equals("Нормальный")) {
            return "#57DB4E";
        } else if (atmState.equals("Неопределённый")) {
            return "#F1CB00";
        } else {
            return "#FF3F3F";
        }
    }

    public String getCompanies() {
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