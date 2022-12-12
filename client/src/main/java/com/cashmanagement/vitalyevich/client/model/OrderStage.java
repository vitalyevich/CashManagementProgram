package com.cashmanagement.vitalyevich.client.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

    private String date;

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY hh:mm").withZone(ZoneId.systemDefault());;

        return formatter.format(stageDate);
    }

    public void setDate(String date) {
        this.date = date;
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

    public OrderStage() {
    }

    public OrderStage(OrderStageId id, Order order, Instant stageDate) {
        this.id = id;
        this.order = order;
        this.stageDate = stageDate;
    }
}