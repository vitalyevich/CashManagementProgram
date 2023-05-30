package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.User;
import com.cashmanagement.vitalyevich.client.model.WithdrawalCash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WithdrawalCashServiceImpl implements WithdrawalCashService{

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<WithdrawalCash> getCashes() {
        String document = """
                     query {
                            withdrawalCashes {
                                id,
                                withdrawalDate,
                                amount,
                                atm {
                                    id,
                                    cassettes {
                                        id
                                        amount,
                                        banknote,
                                    }
                                },
                                cassette {
                                    id,
                                    banknote
                                }
                            }
                         }
                """;

        try {
            Iterable<WithdrawalCash> withdrawalCashes = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("withdrawalCashes")
                    .toEntity(WithdrawalCash[].class).block()));
            return withdrawalCashes;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!");
        }
        return null;
    }

    @Override
    public Iterable<WithdrawalCash> getCash(Integer id) {
        String document = "query {\n" +
                "                              withdrawalCash(id: " + id + ") {\n" +
                "                                id,\n" +
                "                                withdrawalDate,\n" +
                "                                amount,\n" +
                "                                atm {\n" +
                "                                    id\n" +
                "                                }\n" +
                "                                cassette {\n" +
                "                                    id,\n" +
                "                                    cassetteNum,\n" +
                "                                    banknote,\n" +
                "                                    currency,\n" +
                "                                    amount\n" +
                "                                }\n" +
                "                            }"+
                "                         }";

        try {
            Iterable<WithdrawalCash> withdrawalCash = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("withdrawalCash")
                    .toEntity(WithdrawalCash[].class).block()));
            return withdrawalCash;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!");
        }
        return null;
    }
}
