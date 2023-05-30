package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.PlanAtm;
import com.cashmanagement.vitalyevich.server.model.WithdrawalCash;
import com.cashmanagement.vitalyevich.server.repository.AtmRepository;
import com.cashmanagement.vitalyevich.server.repository.PlanAtmRepository;
import com.cashmanagement.vitalyevich.server.repository.WithdrawalCashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawalCashController {

    @Autowired
    private WithdrawalCashRepository withdrawalCashRepository;

    @Autowired
    private PlanAtmRepository planAtmRepository;

    @Autowired
    private AtmRepository atmRepository;

    @QueryMapping
    Iterable<WithdrawalCash> withdrawalCashes () {
        return withdrawalCashRepository.findAll();
    }

    @QueryMapping
    Iterable<WithdrawalCash> withdrawalCash (@Argument Integer id) {
        PlanAtm planAtm = planAtmRepository.findPlanAtmByAtmId(id);
        return withdrawalCashRepository.findByAtm(planAtm.getAtm());
    }
}
