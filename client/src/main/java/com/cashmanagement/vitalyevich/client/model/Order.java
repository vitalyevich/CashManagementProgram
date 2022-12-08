package com.cashmanagement.vitalyevich.client.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Order {

    private Integer id;

    private PlanAtm plan;

    private String stage;

    private LocalDate collectionDate;

    private User user;

    private Set<OrderStage> orderStages = new LinkedHashSet<>();

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

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
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