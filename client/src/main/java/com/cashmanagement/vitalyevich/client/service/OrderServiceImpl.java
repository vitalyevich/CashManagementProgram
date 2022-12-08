package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<Order> getOrders() {
        String document = """
                      query {
                          orders {
                              id,
                              plan {
                                  atm { 
                                  id,
                                  atmUid,
                                  companies {
                                  companyName
                                  }
                              }
                              },
                              stage,
                              collectionDate,
                              user { 
                              id,
                              firstName,
                              lastName
                              }
                          }
                      }
                """;

        try {
            Iterable<Order> orders = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("orders")
                    .toEntity(Order[].class).block()));
            return orders;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<StorageOrder> getStorageOrders() {
        String document = """
                      query {
                           storageOrders {
                           id,
                           order {
                           stage,
                               plan {
                                   atm {
                                   id,
                                   atmUid,
                                       companies {
                                       companyName
                                       }
                                   }
                               }
                           },
                           orderDate,
                               user {
                               id,
                               firstName,
                               lastName
                               }
                           }
                       }
                """;

        try {
            Iterable<StorageOrder> storageOrders = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("storageOrders")
                    .toEntity(StorageOrder[].class).block()));
            return storageOrders;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<BrigadeOrder> getBrigadeOrders() {
        String document = """
                      query {
                           brigadeOrders {
                           id,
                           order {
                           stage,
                               plan {
                                   atm {
                                   id,
                                   atmUid,
                                       companies {
                                       companyName
                                       }
                                   }
                               }
                           },
                           brigade {
                           id,
                           brigadeName
                           },
                           orderDate,
                               user {
                               id,
                               firstName,
                               lastName
                               }
                           }
                       }
                """;

        try {
            Iterable<BrigadeOrder> brigadeOrders = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("brigadeOrders")
                    .toEntity(BrigadeOrder[].class).block()));
            return brigadeOrders;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<OrderStage> getOrderStages() {
        String document = """
                      query {
                           orderStages {
                               id {
                                   orderId,
                                   stageId
                               },
                               stageDate
                           }
                       }
                """;

        try {
            Iterable<OrderStage> orders = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("orderStages")
                    .toEntity(OrderStage[].class).block()));
            return orders;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
