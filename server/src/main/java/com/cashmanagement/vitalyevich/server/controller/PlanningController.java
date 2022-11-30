package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.PlanAtm;
import com.cashmanagement.vitalyevich.server.repository.PlanAtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PlanningController {

    @Autowired
    private final PlanAtmRepository planAtmRepository;

    public PlanningController(PlanAtmRepository planAtmRepository) {
        this.planAtmRepository = planAtmRepository;
    }

    @QueryMapping
    Iterable<PlanAtm> plans () {
        return planAtmRepository.findAll();
    }
}
