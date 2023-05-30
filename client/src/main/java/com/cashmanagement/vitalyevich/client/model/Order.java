package com.cashmanagement.vitalyevich.client.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

public class Order extends ColorTable {

    private Integer id;

    private PlanAtm plan;

    private String stage;

    private String status;

    private LocalDate orderDate;

    private String date;

    public String getDate() {
        return orderDate.format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
    }

    public void setDate(String date) {
        this.date = date;
    }

    private User user;

    private Set<OrderStage> orderStages = new LinkedHashSet<>();

    private String marked;

    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }

    @Override
    public String getColorFirst() {
        if (status.equals("Рассчитан")) {
            return "#3AACED";
        } else if (status.equals("Передан на исполнение") || status.equals("Подтвержден")) {
            return "#57DB4E";
        }
        else {
            return "#FF3F3F";
        }
    }

    @Override
    public String getColorSecond() {
        if (stage.equals("Генерация заказа наличных денег")) {
            return "#3AACED";
        }
        else if (stage.equals("Принятие заказа наличных денег")) {
            return "#57DB4E";
        }
        else {
            return "#FF3F3F";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<OrderStage> getOrderStages() {
        return orderStages;
    }

    public void setOrderStages(Set<OrderStage> orderStages) {
        this.orderStages = orderStages;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public PlanAtm getPlan() {
        return plan;
    }

    public void setPlan(PlanAtm plan) {
        this.plan = plan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}