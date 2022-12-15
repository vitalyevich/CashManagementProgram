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
                                        amount
                                    }
                                },
                                cassette {
                                    id
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
}
