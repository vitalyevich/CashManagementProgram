package com.cashmanagement.vitalyevich.client.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class PlanAtm extends ColorTable {

    private Integer id;

    private Atm atm;

    private String planMethod;

    private String status;

    private Integer planPeriod;

    @Override
    public String getColorFirst() {

        if (status.equals("Рассчитан")) {
            return "#3AACED";
        } else if (status.equals("Изменен")) {
            return "#F1CB00";
        } else {
            return "#FF3F3F";
        }
    }

    private Set<Cassette> cassettes = new LinkedHashSet<>();

    public Set<Cassette> getCassettes() {
        return cassettes;
    }

    public void setCassettes(Set<Cassette> cassettes) {
        this.cassettes = cassettes;
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
}