package com.cashmanagement.vitalyevich.client.model;

import java.time.LocalDate;

public class OrderStage {

    private OrderStageId id;

    private Order order;

    private LocalDate stageDate;

    public LocalDate getStageDate() {
        return stageDate;
    }

    public void setStageDate(LocalDate stageDate) {
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