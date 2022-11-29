package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.Company;
import com.cashmanagement.vitalyevich.client.model.Role;
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
                             roles {
                             id,
                             roleName
                             }
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

    @Override
    public Iterable<Role> getRoles() {
        String document = """
                     query {
                             roles {
                             id
                             roleName
                             }
                         }
                """;

        try {
            Iterable<Role> roles = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("roles")
                    .toEntity(Role[].class).block()));
            return roles;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<Company> getCompany() {
        String document = """
                     query {
                             companies {
                             id
                             companyName
                             }
                         }
                """;

        try {
            Iterable<Company> companies = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("companies")
                    .toEntity(Company[].class).block()));
            return companies;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
