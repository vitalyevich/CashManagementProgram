package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.StorageOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageOperationRepository extends JpaRepository<StorageOperation, Integer> {
}
