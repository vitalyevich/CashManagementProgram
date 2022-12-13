package com.cashmanagement.vitalyevich.client.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Storage {

    private Integer id;

    private Double banknote;

    private String currency;

    private Integer amount;

    private Integer sumAmount;

    private String marked;

    private Integer balanceMorning;

    private Integer balanceEvening;

    public Integer getBalanceMorning() {
        return balanceMorning;
    }

    public void setBalanceMorning(Integer balanceMorning) {
        this.balanceMorning = balanceMorning;
    }

    public Integer getBalanceEvening() {
        return balanceEvening;
    }

    public void setBalanceEvening(Integer balanceEvening) {
        this.balanceEvening = balanceEvening;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }

    private String companyName;

    private Integer amountOperationPlus;

    private Integer amountOperationMinus;

    public Integer getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Integer sumAmount) {
        this.sumAmount = sumAmount;
    }

    public Integer getAmountOperationPlus() {
        return amountOperationPlus;
    }

    public void setAmountOperationPlus(Integer amountOperationPlus) {
        this.amountOperationPlus = amountOperationPlus;
    }

    public Integer getAmountOperationMinus() {
        return amountOperationMinus;
    }

    public void setAmountOperationMinus(Integer amountOperationMinus) {
        this.amountOperationMinus = amountOperationMinus;
    }

    public String getCompanyName() {
        if (companies == null) {
            return " ";
        }
        if (!companies.iterator().hasNext()) {
            return " ";
        }
        return companies.iterator().next().getCompanyName();
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private Set<Company> companies = new LinkedHashSet<>();

    private Set<StorageOperation> storageOperations = new LinkedHashSet<>();

    public Set<StorageOperation> getStorageOperations() {
        return storageOperations;
    }

    public void setStorageOperations(Set<StorageOperation> storageOperations) {
        this.storageOperations = storageOperations;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Storage() {
    }

    public Storage(Integer id, Double banknote, String currency, Integer amount) {
        this.id = id;
        this.banknote = banknote;
        this.currency = currency;
        this.amount = amount;
    }
}