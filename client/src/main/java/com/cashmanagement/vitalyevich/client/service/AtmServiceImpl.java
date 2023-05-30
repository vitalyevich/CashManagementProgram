package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.Cassette;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import com.cashmanagement.vitalyevich.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                              atmState,
                              cassettes {
                                  id,
                                  cassetteNum,
                                  banknote,
                                  currency,
                                  amount
                              },
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

    @Override
    public Atm getAtm(Integer id) {
        String document = "query {\n" +
                "                          atm(id :"+id+") {\n" +
                "                              id,\n" +
                "                              atmUid,\n" +
                "                              cashState,\n" +
                "                              atmState\n" +
                "                              cassettes {\n" +
                "                              id,\n" +
                "                              cassetteNum,\n" +
                "                              banknote,\n" +
                "                              currency,\n" +
                "                              amount,\n" +
                "                              }\n" +
                "                          }\n" +
                "                      }";

        try {
            Atm atm = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("atm")
                    .toEntity(Atm.class).block());
            return atm;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<Cassette> saveCassettes(List<Cassette> cassetteList) {
        String document = "mutation {\n" +
                "  createCassettes(\n" +
                "    cassette: [\n";
        for (Cassette cassette: cassetteList) {
            document +=
                    "      {\n" +
                            "        banknote: "+cassette.getBanknote()+",\n" +
                            "        currency: \""+cassette.getCurrency()+"\",\n" +
                            "        amount: " + cassette.getAmount() + ",\n" +
                            "      }";

        }

        document +=
                "    ],\n" +
                "  ) {\n" +
                "    id\n" +
                "  }\n" +
                "}";


        try {
            Iterable<Cassette> cassettes = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("createCassettes")
                    .toEntity(Cassette[].class).block()));
            return cassettes;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
