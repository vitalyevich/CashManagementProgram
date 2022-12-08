package com.cashmanagement.vitalyevich.client.model;
import java.io.Serializable;
import java.util.Objects;

public class OrderStageId implements Serializable {
    private static final long serialVersionUID = 1943068406543052554L;

    private Integer orderId;

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
        if (o == null || getClass() != o.getClass()) return false;
        OrderStageId that = (OrderStageId) o;
        return orderId.equals(that.orderId) && stageId.equals(that.stageId);
    }
}