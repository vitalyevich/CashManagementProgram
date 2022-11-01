package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.User;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<User> getUsers() {
        String document = """
                     query {
                             users {
                             id
                             firstName
                             lastName
                             phone
                             email
                             }
                         }
                """;

        try {
            Iterable<User> users = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("users")
                    .toEntity(User[].class).block()));
            return users;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
