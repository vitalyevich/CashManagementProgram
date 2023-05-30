package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private PlanAtm plan;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "stage", length = 50)
    private String stage;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(PlanAtm plan, String status, String stage, LocalDate orderDate, User user, Set<OrderStage> orderStages) {
        this.plan = plan;
        this.status = status;
        this.stage = stage;
        this.orderDate = orderDate;
        this.user = user;
        this.orderStages = orderStages;
    }

    public Order(Integer id, PlanAtm plan, String status, String stage, LocalDate orderDate, User user, Set<OrderStage> orderStages) {
        this.id = id;
        this.plan = plan;
        this.status = status;
        this.stage = stage;
        this.orderDate = orderDate;
        this.user = user;
        this.orderStages = orderStages;
    }
}