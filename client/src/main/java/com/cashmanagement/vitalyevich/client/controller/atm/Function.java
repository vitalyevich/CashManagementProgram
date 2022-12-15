package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.model.Cassette;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import com.cashmanagement.vitalyevich.client.model.WithdrawalCash;
import com.cashmanagement.vitalyevich.client.service.PlanningServiceImpl;
import com.cashmanagement.vitalyevich.client.service.WithdrawalCashService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class Function {

    @Autowired
    private WithdrawalCashService withdrawalCashService;

    @Autowired
    private PlanningServiceImpl planningService;

    public List<Cassette> staticForecast (String date, Integer period, Integer planId) {

        List<WithdrawalCash> withdrawalCashes = (List<WithdrawalCash>) withdrawalCashService.getCashes();
        PlanAtm planAtm = planningService.getPlan(planId);

        List<WithdrawalCash> withdrawalCashes1 = new LinkedList<>();
        for (WithdrawalCash withdrawalCash: withdrawalCashes) {
            if (withdrawalCash.getAtm().getId().equals(planAtm.getAtm().getId())) {
                withdrawalCashes1.add(withdrawalCash);
            }
        }

        for (WithdrawalCash withdrawalCash: withdrawalCashes1) {

        }


        return null;
    }

    public List<Cassette> userForecast (String date, Integer period, Integer planId) {
        return null;
    }
}
