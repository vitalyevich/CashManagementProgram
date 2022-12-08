package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.BrigadeOrder;
import com.cashmanagement.vitalyevich.client.model.Order;
import com.cashmanagement.vitalyevich.client.model.OrderStage;
import com.cashmanagement.vitalyevich.client.model.StorageOrder;

public interface OrderService {

    Iterable<Order> getOrders();

    Iterable<StorageOrder> getStorageOrders();

    Iterable<BrigadeOrder> getBrigadeOrders();

    Iterable<OrderStage> getOrderStages();

}
