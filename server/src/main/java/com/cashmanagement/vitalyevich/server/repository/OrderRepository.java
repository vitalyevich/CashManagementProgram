package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Order;
import com.cashmanagement.vitalyevich.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByPlanId(Integer id);

    Order findOrderById(Integer id);

    Iterable<Order> findByOrderByIdAsc();
}
