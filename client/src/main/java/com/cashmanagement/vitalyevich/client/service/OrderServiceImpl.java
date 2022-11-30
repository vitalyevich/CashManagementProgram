package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.Order;
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
}
