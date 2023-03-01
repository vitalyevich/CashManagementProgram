package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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
                              cassettes {
                                  id,
                                  banknote,
                                  currency,
                                  amount
                                  }
                                  atm { 
                                  id,
                                  atmUid,
                                  companies {
                                  companyName
                                  }
                              }
                              },
                              stage,
                              status,
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
            Iterable<Order> orders = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("orders")
                    .toEntity(Order[].class).block()));
            return orders;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    // by planId
    @Override
    public Order getOrder(Integer id) {
        String document = "query {\n" +
                "    order(id: "+id+") {\n" +
                "        id,\n" +
                "        orderDate,\n" +
                "        stage,\n" +
                "        status,\n" +
                "        plan {\n" +
                "        id,\n" +
                "        },\n" +
                "        user {\n" +
                "        id,\n" +
                "        },\n" +
                "    }\n" +
                "}";

        try {
            Order order = graphClient.httpGraphQlClient().document(document)
                    .retrieve("order")
                    .toEntity(Order.class).block();
            return order;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Order getOrderById(Integer id) {
        String document = "query {\n" +
                "    orderById(id: "+id+") {\n" +
                "        id,\n" +
                "        orderDate,\n" +
                "        stage,\n" +
                "        status,\n" +
                "        plan {\n" +
                "        id,\n" +
                "        cassettes {\n" +
                "        id,\n" +
                "        banknote,\n" +
                "        currency,\n" +
                "        amount,\n" +
                "        }\n" +
                "        },\n" +
                "        user {\n" +
                "        id,\n" +
                "        },\n" +
                "    }\n" +
                "}";

        try {
            Order order = graphClient.httpGraphQlClient().document(document)
                    .retrieve("orderById")
                    .toEntity(Order.class).block();
            return order;
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
                           id,
                           status,
                           stage,
                               plan {
                                   atm {
                                   id,
                                   atmUid,
                                       companies {
                                       companyName
                                       }
                                   },
                                   cassettes {
                                  id,
                                  cassetteNum,
                                  banknote,
                                  currency,
                                  amount
                              },
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

    @Override
    public Order updateOrder(Order order, Integer planId, Integer userId) {
        String document = "mutation {\n" +
                "    updateOrder(order: {\n" +
                "        id: "+order.getId()+",\n" +
                "        stage: \""+order.getStage()+"\",\n" +
                "        status: \""+order.getStatus()+"\",\n" +
                "        orderDate: \""+order.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+"\",\n" +
                "    }, plan: {\n" +
                "        id: "+planId+"\n" +
                "    }, user: {\n" +
                "        id: "+userId+"\n" +
                "    }) {\n" +
                "        id\n" +
                "    }\n" +
                "}";

        try {
            Order order1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("updateOrder")
                    .toEntity(Order.class).block());
            return order1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Order saveOrder(Order order, Integer planId, Integer userId) {
        String document = "mutation {\\n\" +\n" +
                "                \"    createOrder(order: {\\n\" +\n" +
                "                \"        stage: \""+order.getStage()+"\",\n" +
                "        status: \""+order.getStatus()+"\",\n" +
                "        orderDate: \""+order.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+"\",\n" +
                "    }, plan: {\n" +
                "        id: "+planId+"\n" +
                "    }, user: {\n" +
                "        id: "+userId+"\n" +
                "    }) {\n" +
                "        id\n" +
                "    }\n" +
                "}";

        try {
            Order order1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("createOrder")
                    .toEntity(Order.class).block());
            return order1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public BrigadeOrder getBrigadeOrder(Integer orderId) {
        String document = "query {\n" +
                "brigadeOrder(id: "+orderId+") {\n" +
                "    id,\n" +
                "    order {\n" +
                "    id,\n" +
                "    orderDate,\n" +
                "    stage,\n" +
                "    status,\n" +
                "    plan {\n" +
                "        id,\n" +
                "        atm {\n" +
                "        id,\n" +
                "        atmUid,\n" +
                "            companies {\n" +
                "            companyName\n" +
                "            }\n" +
                "        },\n" +
                "        cassettes {\n" +
                "        id,\n" +
                "        cassetteNum,\n" +
                "        banknote,\n" +
                "        currency,\n" +
                "        amount\n" +
                "    },\n" +
                "    }\n" +
                "},\n" +
                "brigade {\n" +
                "id,\n" +
                "brigadeName\n" +
                "},\n" +
                "orderDate,\n" +
                "    user {\n" +
                "    id,\n" +
                "    firstName,\n" +
                "    lastName\n" +
                "    }\n" +
                "}\n" +
                "}";

        try {
            BrigadeOrder brigadeOrder = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("brigadeOrder")
                    .toEntity(BrigadeOrder.class).block());
            return brigadeOrder;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public void saveOrderStage(OrderStage orderStage) {
        String document = "mutation {\n" +
                "    createStage(\n" +
                "        stageDate: \""+orderStage.getStageDate()+"\",\n" +
                "        orderId: "+orderStage.getOrder().getId()+",\n" +
                "        stageId: "+orderStage.getId().getStageId()+"\n" +
                "    ) {\n" +
                "        stageDate\n" +
                "    }\n" +
                "}";

        try {
            OrderStage orderStage1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("createStage")
                    .toEntity(OrderStage.class).block());
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
    }

    @Override
    public void deleteOrder(Integer id) {
        String document = "mutation {\n" +
                "        deleteOrder(id: "+id+")\n" +
                "    }";

        try {
            graphClient.httpGraphQlClient().document(document)
                    .retrieve("deleteOrder").toEntity(Order.class).block();
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
    }

    @Override
    public void deleteByOrder(Integer id) {
        String document = "mutation {\n" +
                "        deleteByOrder(id: "+id+")\n" +
                "    }";

        try {
            graphClient.httpGraphQlClient().document(document)
                    .retrieve("deleteByOrder").toEntity(Order.class).block();
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
    }

    @Override
    public StorageOrder getStorageOrder(Integer orderId) {
        String document = "query {\n" +
                "                           storageOrder(id:"+orderId+") {\n" +
                "                           id,\n" +
                "                           order {\n" +
                "                           id,\n" +
                "                           stage,\n" +
                "                               plan {\n" +
                "                                   atm {\n" +
                "                                   id,\n" +
                "                                   atmUid,\n" +
                "                                       companies {\n" +
                "                                       companyName\n" +
                "                                       }\n" +
                "                                   }\n" +
                "                               }\n" +
                "                           },\n" +
                "                           orderDate,\n" +
                "                               user {\n" +
                "                               id,\n" +
                "                               firstName,\n" +
                "                               lastName\n" +
                "                               }\n" +
                "                           }\n" +
                "                       }";

        try {
            StorageOrder storageOrder = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("storageOrder")
                    .toEntity(StorageOrder.class).block());
            return storageOrder;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public BrigadeOrder updateBrigadeOrder(BrigadeOrder brigadeOrder) {
        String document = "mutation {\n" +
                "    updateBrigadeOrder(\n" +
                "        brigadeOrder : {\n" +
                "            id: "+brigadeOrder.getId()+",\n" +
                "            orderDate: \""+brigadeOrder.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))+"\"\n" +
                "        },\n" +
                "        orderId: "+brigadeOrder.getOrder().getId()+",\n" +
                "        brigadeId: "+brigadeOrder.getBrigade().getId()+",\n" +
                "        userId: "+brigadeOrder.getUser().getId()+"\n" +
                "    ) {\n" +
                "        orderDate\n" +
                "    }\n" +
                "}";

        try {
            BrigadeOrder brigadeOrder1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("updateBrigadeOrder")
                    .toEntity(BrigadeOrder.class).block());
            return brigadeOrder1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public StorageOrder updateStorageOrder(StorageOrder storageOrder) {
        String document = "mutation {\n" +
                "    updateStorageOrder(\n" +
                "        storageOrder : {\n" +
                "            id: "+storageOrder.getId()+",\n" +
                "            orderDate: \""+storageOrder.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))+"\"\n" +
                "        },\n" +
                "        orderId: "+storageOrder.getOrder().getId()+",\n" +
                "        userId: "+storageOrder.getUser().getId()+"\n" +
                "    ) {\n" +
                "        orderDate\n" +
                "    }\n" +
                "}";

        try {
            StorageOrder storageOrder1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("updateStorageOrder")
                    .toEntity(StorageOrder.class).block());
            return storageOrder1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public StorageOrder saveStorageOrder(StorageOrder storageOrder) {
        String document = "mutation {\n" +
                "    createStorageOrder(\n" +
                "        storageOrder : {\n" +
                "            orderDate: \""+storageOrder.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))+"\"\n" +
                "        },\n" +
                "        orderId: "+storageOrder.getOrder().getId()+",\n" +
                "        userId: "+storageOrder.getUser().getId()+"\n" +
                "    ) {\n" +
                "        orderDate\n" +
                "    }\n" +
                "}";

        try {
            StorageOrder storageOrder1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("createStorageOrder")
                    .toEntity(StorageOrder.class).block());
            return storageOrder1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public BrigadeOrder deleteBrigadeInOrder(BrigadeOrder brigadeOrder) {
            String document = "mutation {\n" +
                    "    updateBrigadeOrder(\n" +
                    "        brigadeOrder : {\n" +
                    "            id: "+brigadeOrder.getId()+",\n" +
                    "            orderDate: \""+brigadeOrder.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))+"\"\n" +
                    "        },\n" +
                    "        orderId: "+brigadeOrder.getOrder().getId()+",\n" +
                    "        brigadeId: null,\n" +
                    "        userId: "+brigadeOrder.getUser().getId()+"\n" +
                    "    ) {\n" +
                    "        orderDate\n" +
                    "    }\n" +
                    "}";

            try {
                BrigadeOrder brigadeOrder1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                        .retrieve("updateBrigadeOrder")
                        .toEntity(BrigadeOrder.class).block());
                return brigadeOrder1;
            } catch (GraphQlTransportException ex) {
                System.out.println("Ошибка соединения!"); // test
            }
            return null;
        }
}
