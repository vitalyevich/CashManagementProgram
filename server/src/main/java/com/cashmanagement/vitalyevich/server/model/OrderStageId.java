package com.cashmanagement.vitalyevich.server.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderStageId implements Serializable {
    private static final long serialVersionUID = 1943068406543052554L;
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @Column(name = "stage_id", nullable = false)
    private Integer stageId;

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, stageId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderStageId entity = (OrderStageId) o;
        return Objects.equals(this.orderId, entity.orderId) &&
                Objects.equals(this.stageId, entity.stageId);
    }

    public OrderStageId() {
    }

    public OrderStageId(Integer orderId, Integer stageId) {
        this.orderId = orderId;
        this.stageId = stageId;
    }
}