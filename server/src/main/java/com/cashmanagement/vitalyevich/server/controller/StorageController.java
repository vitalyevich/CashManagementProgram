package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.Storage;
import com.cashmanagement.vitalyevich.server.model.StorageOperation;
import com.cashmanagement.vitalyevich.server.repository.StorageOperationRepository;
import com.cashmanagement.vitalyevich.server.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class StorageController {

    @Autowired
    private final StorageRepository storageRepository;

    @Autowired
    private final StorageOperationRepository storageOperationRepository;

    public StorageController(StorageRepository storageRepository, StorageOperationRepository storageOperationRepository) {
        this.storageRepository = storageRepository;
        this.storageOperationRepository = storageOperationRepository;
    }

    @QueryMapping
    Iterable<Storage> storages () {
        return storageRepository.findByOrderByCompaniesAscCurrencyAsc();
    }

    @QueryMapping
    Iterable<StorageOperation> operations (@Argument Integer id) {
        return storageOperationRepository.findAllByUpdateDateAndStorageId(LocalDate.now(), id);
    }


    @QueryMapping
    Optional<Storage> storage (@Argument Integer id) {
        return storageRepository.findById(id);
    }

}
