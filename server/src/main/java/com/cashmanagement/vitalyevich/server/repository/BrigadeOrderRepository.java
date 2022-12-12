package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.BrigadeOrder;
import com.cashmanagement.vitalyevich.server.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrigadeOrderRepository extends JpaRepository<BrigadeOrder, Integer> {

    @Override
    Optional<BrigadeOrder> findById(Integer id);

    void deleteAllByOrder(Order order);

    BrigadeOrder findBrigadeOrderByOrderId(Integer id);


    Iterable<BrigadeOrder> findByOrderByIdAsc();
}
