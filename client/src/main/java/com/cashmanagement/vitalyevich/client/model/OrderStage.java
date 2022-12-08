package com.cashmanagement.vitalyevich.client.model;

import java.time.Instant;

public class OrderStage {

    private OrderStageId id;

    private Order order;

    private Instant stageDate;

    private String stageName;

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Instant getStageDate() {
        return stageDate;
    }

    public void setStageDate(Instant stageDate) {
        this.stageDate = stageDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderStageId getId() {
        return id;
    }

    public void setId(OrderStageId id) {
        this.id = id;
    }
}