package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.*;
import com.cashmanagement.vitalyevich.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final BrigadeOrderRepository brigadeOrderRepository;

    @Autowired
    private final BrigadeRepository brigadeRepository;

    @Autowired
    private final StorageOrderRepository storageOrderRepository;

    @Autowired
    private final OrderStageRepository orderStageRepository;


    public OrderController(OrderRepository orderRepository, BrigadeOrderRepository brigadeOrderRepository, BrigadeRepository brigadeRepository, StorageOrderRepository storageOrderRepository, OrderStageRepository orderStageRepository) {
        this.orderRepository = orderRepository;
        this.brigadeOrderRepository = brigadeOrderRepository;
        this.brigadeRepository = brigadeRepository;
        this.storageOrderRepository = storageOrderRepository;
        this.orderStageRepository = orderStageRepository;
    }

    @QueryMapping
    Iterable<Order> orders () {
        return orderRepository.findAll();
    }

    @QueryMapping
    Iterable<BrigadeOrder> brigadeOrders () {
        return brigadeOrderRepository.findByOrderByIdAsc();
    }

    @QueryMapping
    Iterable<StorageOrder> storageOrders () {
        return storageOrderRepository.findAll();
    }

    @QueryMapping
    Iterable<OrderStage> orderStages () {
        return orderStageRepository.findAll();
    }

    @QueryMapping
    Optional<Order> order (@Argument Integer id) {
        return orderRepository.findByPlanId(id);
    }

    @QueryMapping
    Optional<OrderStage> orderStage (@Argument Integer orderId, @Argument Integer stageId) {
        return orderStageRepository.findById(new OrderStageId(orderId, stageId));
    }

    @QueryMapping
    Optional<BrigadeOrder> brigadeOrder (@Argument Integer id) {
        return brigadeOrderRepository.findById(id);
    }

    @QueryMapping
    Optional<StorageOrder> storageOrder (@Argument Integer id) {
        return storageOrderRepository.findById(id);
    }

    @MutationMapping
    Order updateOrder(@Argument Order order, @Argument PlanAtm plan, @Argument User user) {

        order.setUser(user);
        order.setPlan(plan);
        return orderRepository.save(order);
    }

    @MutationMapping
    Order createOrder(@Argument Order order, @Argument PlanAtm plan, @Argument User user) {

        order.setUser(user);
        order.setPlan(plan);
        return orderRepository.save(order);
    }

    @MutationMapping
    void deleteOrder(@Argument Integer id) {

        StorageOrder storageOrder = storageOrderRepository.findStorageOrderByOrderId(id);
        storageOrderRepository.deleteById(storageOrder.getId());
        try {
            orderStageRepository.deleteById(new OrderStageId(id, 1));
            orderStageRepository.deleteById(new OrderStageId(id, 2));
            orderStageRepository.deleteById(new OrderStageId(id, 3));
            orderStageRepository.deleteById(new OrderStageId(id, 4));
            orderStageRepository.deleteById(new OrderStageId(id, 5));
            orderStageRepository.deleteById(new OrderStageId(id, 6));
            orderStageRepository.deleteById(new OrderStageId(id, 7));
        } catch (Exception e) {
            if (brigadeOrderRepository.findBrigadeOrderByOrderId(id) != null) {
                BrigadeOrder brigadeOrder = brigadeOrderRepository.findBrigadeOrderByOrderId(id);
                brigadeOrderRepository.deleteById(brigadeOrder.getId());
            }
        }
        orderRepository.deleteById(id);
    }

    @MutationMapping
    OrderStage createStage(@Argument String stageDate, @Argument Integer orderId, @Argument Integer stageId) {

        OrderStage orderStage = new OrderStage();
        orderStage.setStageDate(Instant.parse(stageDate));
        orderStage.setOrder(new Order(orderId));
        orderStage.setId(new OrderStageId(orderId, stageId));

        return orderStageRepository.save(orderStage);
    }

    @MutationMapping
    BrigadeOrder updateBrigadeOrder(@Argument BrigadeOrder brigadeOrder, @Argument Integer orderId,
                                    @Argument Integer brigadeId, @Argument Integer userId) {

        if (brigadeId == null) {
            brigadeOrder.setBrigade(null);
        }
        else {
            Optional<Brigade> brigade = brigadeRepository.findById(brigadeId);
            brigadeOrder.setBrigade(brigade.get());
        }
        Order order = new Order(orderId);
        brigadeOrder.setOrder(order);
        User user = new User(userId);
        brigadeOrder.setUser(user);
        return brigadeOrderRepository.save(brigadeOrder);
    }
}
