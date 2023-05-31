package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.Cassette;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import com.cashmanagement.vitalyevich.client.model.Storage;
import com.cashmanagement.vitalyevich.client.model.StorageOperation;

import java.util.List;

public interface StorageService {

    Iterable<Storage> getStorages();

    Iterable<StorageOperation> getStorageOperations(Integer storageId);

    Storage getStorage(Integer id);

    void updateStorage(List<Cassette> cassette);

}
