package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.Storage;
import com.cashmanagement.vitalyevich.server.model.StorageOperation;
import com.cashmanagement.vitalyevich.server.repository.StorageOperationRepository;
import com.cashmanagement.vitalyevich.server.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

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
        return storageRepository.findAll();
    }

    @QueryMapping
    Iterable<StorageOperation> operations () {
        return storageOperationRepository.findAll();
    }

}
