package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.*;

public interface CompanyService {

    Iterable<Country> getCountries();

    Iterable<City> getCities();

    Iterable<Company> getCompany();

    Company saveCompany(Company company, Integer cityId);

}
