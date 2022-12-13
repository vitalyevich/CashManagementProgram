package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.Storage;
import com.cashmanagement.vitalyevich.client.model.StorageOperation;
import com.cashmanagement.vitalyevich.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<Storage> getStorages() {
        String document = """
                     query {
                              storages {
                              id,
                              banknote,
                              currency,
                              amount
                                  companies {
                                  id,
                                  companyName
                                  },
                                  storageOperations {
                                      id,
                                      updateDate,
                                      amountOperation,
                                  }
                              }
                          }
                """;

        try {
            Iterable<Storage> storages = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("storages")
                    .toEntity(Storage[].class).block()));
            return storages;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<StorageOperation> getStorageOperations(Integer storageId) {
        String document = "query {\n" +
                "    operations (id: "+storageId+") {\n" +
                "            id,\n" +
                "            updateDate,\n" +
                "            amountOperation,\n" +
                "        }\n" +
                "}";

        try {
            Iterable<StorageOperation> operations = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("operations")
                    .toEntity(StorageOperation[].class).block()));
            return operations;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Storage getStorage(Integer id) {
        String document = "                     query {\n" +
                "                             storage(id: "+id+") {\n" +
                "                             id,\n" +
                "                             banknote,\n" +
                "                             currency,\n" +
                "                             amount\n" +
                "                                 companies {\n" +
                "                                 id,\n" +
                "                                 companyName\n" +
                "                                 }\n" +
                "                             }\n" +
                "                         }";

        try {
            Storage storage = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("storage")
                    .toEntity(Storage.class).block());
            return storage;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
