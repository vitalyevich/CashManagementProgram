package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.StorageOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.LocalDate;

public interface StorageOperationRepository extends JpaRepository<StorageOperation, Integer> {

    Iterable<StorageOperation> findAllByUpdateDateAndStorageId(LocalDate localDate, Integer id);
}
