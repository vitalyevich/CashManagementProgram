package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "withdrawal_cash")
public class WithdrawalCash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdrawal_id", nullable = false)
    private Integer id;

    @Column(name = "withdrawal_date", nullable = false)
    private LocalDate withdrawalDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "atm_id", nullable = false)
    private Atm atm;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cassette_id", nullable = false)
    private Cassette cassette;

    @Column(name = "amount", nullable = false)
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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