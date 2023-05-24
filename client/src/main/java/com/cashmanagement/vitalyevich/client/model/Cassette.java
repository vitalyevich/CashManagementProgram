package com.cashmanagement.vitalyevich.client.model;

public class Cassette {

    private Integer id;

    private Integer cassetteNum;

    private Double banknote; // int

    private String currency;

    private Integer amount;

    private Integer sumAmount;

    public Integer getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Integer sumAmount) {
        this.sumAmount = sumAmount;
    }

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