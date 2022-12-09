package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.*;
import com.cashmanagement.vitalyevich.server.repository.CityRepository;
import com.cashmanagement.vitalyevich.server.repository.CompanyRepository;
import com.cashmanagement.vitalyevich.server.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class CompanyController {

    @Autowired
    private final CityRepository cityRepository;


    private final CompanyRepository companyRepository;

    @Autowired
    private final CountryRepository countryRepository;

    public CompanyController(CityRepository cityRepository, CompanyRepository companyRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

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
