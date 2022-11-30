package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.Order;

public interface OrderService {

    Iterable<Order> getOrders();
}
