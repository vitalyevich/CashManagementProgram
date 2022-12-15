package com.cashmanagement.vitalyevich.client.model;

import java.time.LocalDate;

public class WithdrawalCash {

    private Integer id;

    private LocalDate withdrawalDate;

    private Atm atm;

    private Cassette cassette;

    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Cassette getCassette() {
        return cassette;
    }

    public void setCassette(Cassette cassette) {
        this.cassette = cassette;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(LocalDate withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}