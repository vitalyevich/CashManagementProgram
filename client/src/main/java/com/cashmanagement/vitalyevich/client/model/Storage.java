package com.cashmanagement.vitalyevich.client.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Storage {

    private Integer id;

    private Double banknote;

    private String currency;

    private Double amount;

    private Set<Company> companies = new LinkedHashSet<>();

    private Set<StorageOperation> storageOperations = new LinkedHashSet<>();

    public Set<StorageOperation> getStorageOperations() {
        return storageOperations;
    }

    public void setStorageOperations(Set<StorageOperation> storageOperations) {
        this.storageOperations = storageOperations;
    }

    public String getCompanies() {
        return companies.iterator().next().getCompanyName();
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}