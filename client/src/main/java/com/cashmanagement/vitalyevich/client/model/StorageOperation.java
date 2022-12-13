package com.cashmanagement.vitalyevich.client.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class StorageOperation {

    private Integer id;

    private Storage storage;

    private LocalDate updateDate;

    private Integer amountOperation;

    private String date;

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");;

        return formatter.format(updateDate);
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Integer getAmountOperation() {
        return amountOperation;
    }

    public void setAmountOperation(Integer amountOperation) {
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