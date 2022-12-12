package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Override
    Optional<Order> findById(Integer id);
}
