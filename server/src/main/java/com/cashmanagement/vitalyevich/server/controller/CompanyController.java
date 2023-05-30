package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.*;
import com.cashmanagement.vitalyevich.server.repository.CityRepository;
import com.cashmanagement.vitalyevich.server.repository.CompanyRepository;
import com.cashmanagement.vitalyevich.server.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Set;

@RestController
public class CompanyController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CountryRepository countryRepository;

    @QueryMapping
    Iterable<City> cities () {
        return cityRepository.findAll();
    }

    @QueryMapping
    Iterable<Country> countries () {
        return countryRepository.findAll();
    }

    @MutationMapping
    Company createCompany(@Argument Company company, @Argument City city) {

        Set<City> cities = new LinkedHashSet<>();
        cities.add(city);
        company.setCities(cities);
        return companyRepository.save(company);
    }


    @QueryMapping
    Iterable<Company> companies () {
        return companyRepository.findAll();
    }
}
