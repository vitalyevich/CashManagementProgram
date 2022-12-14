package com.cashmanagement.vitalyevich.client.model;

import javax.persistence.*;

@Entity
@Table(name = "cassettes")
public class Cassette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cassette_id", nullable = false)
    private Integer id;

    private Integer cassetteNum;

    private Double banknote;

    private String currency;

    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBanknote() {
        if(banknote % 1 == 0) {
            return String.valueOf(Math.round(banknote));
        } else {
            return String.valueOf(banknote);
        }
    }

    public void setBanknote(Double banknote) {
        this.banknote = banknote;
    }

    public Integer getCassetteNum() {
        return cassetteNum;
    }

    public void setCassetteNum(Integer cassetteNum) {
        this.cassetteNum = cassetteNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}