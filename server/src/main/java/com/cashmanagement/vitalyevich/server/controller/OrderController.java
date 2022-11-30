package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.BrigadeOrder;
import com.cashmanagement.vitalyevich.server.model.Order;
import com.cashmanagement.vitalyevich.server.model.StorageOrder;
import com.cashmanagement.vitalyevich.server.repository.BrigadeOrderRepository;
import com.cashmanagement.vitalyevich.server.repository.OrderRepository;
import com.cashmanagement.vitalyevich.server.repository.StorageOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final BrigadeOrderRepository brigadeOrderRepository;

    @Autowired
    private final StorageOrderRepository storageOrderRepository;

    public OrderController(OrderRepository orderRepository, BrigadeOrderRepository brigadeOrderRepository, StorageOrderRepository storageOrderRepository) {
        this.orderRepository = orderRepository;
        this.brigadeOrderRepository = brigadeOrderRepository;
        this.storageOrderRepository = storageOrderRepository;
    }

    @QueryMapping
    Iterable<Order> orders () {
        return orderRepository.findAll();
    }

    @QueryMapping
    Iterable<BrigadeOrder> brigadeOrders () {
        return brigadeOrderRepository.findAll();
    }

    @QueryMapping
    Iterable<StorageOrder> storageOrders () {
        return storageOrderRepository.findAll();
    }
}
