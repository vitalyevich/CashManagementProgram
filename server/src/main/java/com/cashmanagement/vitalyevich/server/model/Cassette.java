package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;

@Entity
@Table(name = "cassettes")
public class Cassette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cassette_id", nullable = false)
    private Integer id;

    @Column(name = "cassette_num", nullable = false)
    private Integer cassetteNum;

    @Column(name = "banknote", nullable = false)
    private Double banknote;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "amount", nullable = false)
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

    public Double getBanknote() {
        return banknote;
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