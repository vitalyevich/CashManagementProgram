package com.cashmanagement.vitalyevich.client.model;

import java.time.LocalDate;


public class StorageOperation {

    private Integer id;

    private Storage storage;

    private LocalDate updateDate;

    private Double amountOperation;

    public Double getAmountOperation() {
        return amountOperation;
    }

    public void setAmountOperation(Double amountOperation) {
        this.amountOperation = amountOperation;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}