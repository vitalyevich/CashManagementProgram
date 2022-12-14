package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.BrigadeOrder;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;

public interface PlanningService {

    Iterable<PlanAtm> getPlans();

    PlanAtm getPlan(Integer id);

    PlanAtm updatePlanAtm(PlanAtm planAtm);
}
