package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.Storage;
import com.cashmanagement.vitalyevich.server.model.StorageOperation;
import com.cashmanagement.vitalyevich.server.repository.StorageOperationRepository;
import com.cashmanagement.vitalyevich.server.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@RestController
public class StorageController {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private StorageOperationRepository storageOperationRepository;

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
