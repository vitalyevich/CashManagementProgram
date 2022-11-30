package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.City;
import com.cashmanagement.vitalyevich.server.model.CityCompany;
import com.cashmanagement.vitalyevich.server.model.Country;
import com.cashmanagement.vitalyevich.server.repository.CityCompanyRepository;
import com.cashmanagement.vitalyevich.server.repository.CityRepository;
import com.cashmanagement.vitalyevich.server.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CompanyController {

    @Autowired
    private final CityRepository cityRepository;

    @Autowired
    private final CityCompanyRepository cityCompanyRepository;

    @Autowired
    private final CountryRepository countryRepository;

    public CompanyController(CityRepository cityRepository, CityCompanyRepository cityCompanyRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.cityCompanyRepository = cityCompanyRepository;
        this.countryRepository = countryRepository;
    }

    @QueryMapping
    Iterable<City> cities () {
        return cityRepository.findAll();
    }

    @QueryMapping
    Iterable<CityCompany> cityCompanies () {
        return cityCompanyRepository.findAll();
    }

    @QueryMapping
    Iterable<Country> countries () {
        return countryRepository.findAll();
    }
}
