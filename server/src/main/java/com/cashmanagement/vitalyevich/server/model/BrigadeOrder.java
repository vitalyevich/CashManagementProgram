package com.cashmanagement.vitalyevich.server.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "brigade_orders")
public class BrigadeOrder {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_id")
    private Brigade brigade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
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

    public BrigadeOrder() {
    }

    public BrigadeOrder(Integer id, Order order, LocalDate orderDate, Brigade brigade, User user) {
        this.id = id;
        this.order = order;
        this.orderDate = orderDate;
        this.brigade = brigade;
        this.user = user;
    }

    public BrigadeOrder(Order order, LocalDate orderDate, Brigade brigade, User user) {
        this.order = order;
        this.orderDate = orderDate;
        this.brigade = brigade;
        this.user = user;
    }
}