package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id", nullable = false)
    private Integer id;

    @Column(name = "banknote", nullable = false)
    private Double banknote;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToMany
    @JoinTable(name = "company_storages",
            joinColumns = @JoinColumn(name = "storage_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> companies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "storage")
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

    public Storage(Double banknote, String currency, Integer amount, Set<Company> companies, Set<StorageOperation> storageOperations) {
        this.banknote = banknote;
        this.currency = currency;
        this.amount = amount;
        this.companies = companies;
        this.storageOperations = storageOperations;
    }

    public Storage(Integer id, Double banknote, String currency, Integer amount, Set<Company> companies, Set<StorageOperation> storageOperations) {
        this.id = id;
        this.banknote = banknote;
        this.currency = currency;
        this.amount = amount;
        this.companies = companies;
        this.storageOperations = storageOperations;
    }

    public Storage() {
    }
}