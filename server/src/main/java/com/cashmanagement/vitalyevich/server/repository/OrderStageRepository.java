package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.OrderStage;
import com.cashmanagement.vitalyevich.server.model.OrderStageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStageRepository extends JpaRepository<OrderStage, OrderStageId> {
}
