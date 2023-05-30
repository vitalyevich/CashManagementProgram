package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.StorageOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageOrderRepository extends JpaRepository<StorageOrder, Integer> {

    void deleteStorageOrderByOrderId(Integer id);

    StorageOrder findStorageOrderByOrderId(Integer id);
}
