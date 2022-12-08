package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.WithdrawalCash;
import com.cashmanagement.vitalyevich.server.repository.WithdrawalCashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WithdrawalCashController {

    @Autowired
    private final WithdrawalCashRepository withdrawalCashRepository;

    public WithdrawalCashController(WithdrawalCashRepository withdrawalCashRepository) {
        this.withdrawalCashRepository = withdrawalCashRepository;
    }

    @QueryMapping
    Iterable<WithdrawalCash> withdrawalCashes () {
        return withdrawalCashRepository.findAll();
    }
}
