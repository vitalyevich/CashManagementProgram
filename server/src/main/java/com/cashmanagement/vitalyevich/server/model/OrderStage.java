package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "order_stages")
public class OrderStage {
    @EmbeddedId
    private OrderStageId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "stage_date", nullable = false)
    private Instant stageDate;

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