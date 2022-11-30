package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.StorageOperation;
import com.cashmanagement.vitalyevich.server.model.StorageOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageOrderRepository extends JpaRepository<StorageOrder, Integer> {
}
