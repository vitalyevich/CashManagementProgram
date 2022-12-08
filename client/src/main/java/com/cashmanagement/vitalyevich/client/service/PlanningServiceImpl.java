package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlanningServiceImpl implements PlanningService {

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<PlanAtm> getPlans() {
        String document = """
                      query {
                          plans {
                              id,
                              atm {
                              id,
                              atmUid,
                              cashState,
                              atmState
                              },
                              planMethod,
                              status,
                              planPeriod
                          }
                      }
                """;

        try {
            Iterable<PlanAtm> planAtms = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("plans")
                    .toEntity(PlanAtm[].class).block()));
            return planAtms;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
