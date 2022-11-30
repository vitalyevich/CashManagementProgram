package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AtmServiceImpl implements AtmService{

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<Atm> getAtms() {
        String document = """
                      query {
                          atms {
                              id,
                              atmUid,
                              cashState,
                              atmState
                          }
                      }
                """;

        try {
            Iterable<Atm> atms = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("atms")
                    .toEntity(Atm[].class).block()));
            return atms;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
