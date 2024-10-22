package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.*;
import com.cashmanagement.vitalyevich.server.repository.PlanAtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class PlanningController {

    @Autowired
    private PlanAtmRepository planAtmRepository;

    @QueryMapping
    Iterable<PlanAtm> plans () {
        //return planAtmRepository.findByOrderByIdAscCassettesAsc();
        return planAtmRepository.findByOrderByIdAsc();
    }

    @QueryMapping
    Optional<PlanAtm> plan (@Argument Integer id) {
        return planAtmRepository.findById(id);
    }
}
