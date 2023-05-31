package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.Atm;
import com.cashmanagement.vitalyevich.server.model.Cassette;
import com.cashmanagement.vitalyevich.server.model.PlanAtm;
import com.cashmanagement.vitalyevich.server.model.User;
import com.cashmanagement.vitalyevich.server.repository.AtmRepository;
import com.cashmanagement.vitalyevich.server.repository.CassetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// @Service
@RestController
public class AtmController {
    @Autowired
    private AtmRepository atmRepository;
    @Autowired
    private CassetteRepository cassetteRepository;


    @QueryMapping
    Iterable<Atm> atms () {
        return atmRepository.findByOrderById();
    }

    @QueryMapping
    Optional<Atm> atm (@Argument Integer id) {
        return atmRepository.findAllById(id);
    }

    @QueryMapping
    Iterable<Cassette> cassettes () { return cassetteRepository.findByOrderByCassetteNumAsc(); }


    @MutationMapping
    List <Cassette> createCassettes(@Argument List<Cassette> cassette) {

        int i = 1;
        for (Cassette cassette1: cassette) {
            cassette1.setCassetteNum(i);
            cassetteRepository.save(cassette1);
            i++;
        }

        return cassette;
    }

    @MutationMapping
    Iterable <Cassette> updateCassettes(@Argument List<Cassette> cassette) {

        for (Cassette cassette1: cassette) {
            cassetteRepository.save(cassette1);
        }

        return cassette;
    }
}
