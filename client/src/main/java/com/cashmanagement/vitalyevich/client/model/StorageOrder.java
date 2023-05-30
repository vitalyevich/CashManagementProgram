package com.cashmanagement.vitalyevich.client.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StorageOrder {

    private Integer id;

    private Order order;

    private LocalDate orderDate;

    private String date;

    public String getDate() {
        return orderDate.format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
    }

    public void setDate(String date) {
        this.date = date;
    }

    private User user;

    private String marked;

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StorageOrder() {
    }

    public StorageOrder(Integer id, Order order, LocalDate orderDate, User user) {
        this.id = id;
        this.order = order;
        this.orderDate = orderDate;
        this.user = user;
    }

    public StorageOrder(Order order, LocalDate orderDate, User user) {
        this.order = order;
        this.orderDate = orderDate;
        this.user = user;
    }
}