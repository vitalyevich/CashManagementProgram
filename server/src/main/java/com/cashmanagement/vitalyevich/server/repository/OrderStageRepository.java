package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.OrderStage;
import com.cashmanagement.vitalyevich.server.model.OrderStageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderStageRepository extends JpaRepository<OrderStage, OrderStageId> {


    Iterable<OrderStage> findByOrderId(Integer id);
}
