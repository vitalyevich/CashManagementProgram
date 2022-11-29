package com.cashmanagement.vitalyevich.client.model;

public class Cassette {

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