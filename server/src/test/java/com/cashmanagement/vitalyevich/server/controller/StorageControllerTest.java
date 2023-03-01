package com.cashmanagement.vitalyevich.server.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashmanagement.vitalyevich.server.model.Storage;
import com.cashmanagement.vitalyevich.server.model.StorageOperation;
import com.cashmanagement.vitalyevich.server.repository.StorageOperationRepository;
import com.cashmanagement.vitalyevich.server.repository.StorageRepository;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StorageController.class})
@ExtendWith(SpringExtension.class)
class StorageControllerTest {
    @Autowired
    private StorageController storageController;

    @MockBean
    private StorageOperationRepository storageOperationRepository;

    @MockBean
    private StorageRepository storageRepository;

    /**
     * Method under test: {@link StorageController#storages()}
     */
    @Test
    void testStorages() {
        when(this.storageRepository.findByOrderByCompaniesAscCurrencyAsc())
                .thenReturn((Iterable<Storage>) mock(Iterable.class));
        this.storageController.storages();
        verify(this.storageRepository).findByOrderByCompaniesAscCurrencyAsc();
    }

    /**
     * Method under test: {@link StorageController#operations(Integer)}
     */
    @Test
    void testOperations() {
        when(this.storageOperationRepository.findAllByUpdateDateAndStorageId((java.time.LocalDate) any(), (Integer) any()))
                .thenReturn((Iterable<StorageOperation>) mock(Iterable.class));
        this.storageController.operations(1);
        verify(this.storageOperationRepository).findAllByUpdateDateAndStorageId((java.time.LocalDate) any(),
                (Integer) any());
    }

    /**
     * Method under test: {@link StorageController#storage(Integer)}
     */
    @Test
    void testStorage() {
        Storage storage = new Storage();
        storage.setAmount(10);
        storage.setBanknote(10.0d);
        storage.setCompanies(new HashSet<>());
        storage.setCurrency("GBP");
        storage.setId(1);
        storage.setStorageOperations(new HashSet<>());
        Optional<Storage> ofResult = Optional.of(storage);
        when(this.storageRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Storage> actualStorageResult = this.storageController.storage(1);
        assertSame(ofResult, actualStorageResult);
        assertTrue(actualStorageResult.isPresent());
        verify(this.storageRepository).findById((Integer) any());
    }
}

