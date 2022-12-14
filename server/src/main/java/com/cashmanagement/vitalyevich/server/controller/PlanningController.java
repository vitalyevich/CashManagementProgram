package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.*;
import com.cashmanagement.vitalyevich.server.repository.PlanAtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PlanningController {

    @Autowired
    private final PlanAtmRepository planAtmRepository;

    public PlanningController(PlanAtmRepository planAtmRepository) {
        this.planAtmRepository = planAtmRepository;
    }

    @QueryMapping
    Iterable<PlanAtm> plans () {
        return planAtmRepository.findByOrderByIdAsc();
    }

    @QueryMapping
    Optional<PlanAtm> plan (@Argument Integer id) {
        return planAtmRepository.findById(id);
    }

    @MutationMapping
    PlanAtm updatePlan(@Argument PlanAtm plan, @Argument Atm atm, @Argument User user) {

        plan.setAtm(atm);
        plan.setUser(user);
        return planAtmRepository.save(plan);
    }

}
