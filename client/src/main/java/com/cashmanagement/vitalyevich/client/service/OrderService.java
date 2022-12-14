package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.*;

public interface OrderService {

    Iterable<Order> getOrders();

    Order getOrder(Integer id);

    Iterable<StorageOrder> getStorageOrders();

    Iterable<BrigadeOrder> getBrigadeOrders();

    Iterable<OrderStage> getOrderStages();

    Order updateOrder(Order order, Integer planId, Integer userId);

    Order saveOrder(Order order, Integer planId, Integer userId);

    BrigadeOrder getBrigadeOrder(Integer orderId);

    void saveOrderStage(OrderStage orderStage);

    void deleteOrder(Integer id);

    StorageOrder getStorageOrder(Integer orderId);

    BrigadeOrder updateBrigadeOrder(BrigadeOrder brigadeOrder);

    BrigadeOrder deleteBrigadeInOrder(BrigadeOrder brigadeOrder);

}
