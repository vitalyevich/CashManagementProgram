package com.cashmanagement.vitalyevich.server.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashmanagement.vitalyevich.server.model.City;
import com.cashmanagement.vitalyevich.server.model.Company;
import com.cashmanagement.vitalyevich.server.model.Country;
import com.cashmanagement.vitalyevich.server.repository.CityRepository;
import com.cashmanagement.vitalyevich.server.repository.CompanyRepository;
import com.cashmanagement.vitalyevich.server.repository.CountryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CompanyController.class})
@ExtendWith(SpringExtension.class)
class CompanyControllerTest {
    @MockBean
    private CityRepository cityRepository;

    @Autowired
    private CompanyController companyController;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private CountryRepository countryRepository;

    /**
     * Method under test: {@link CompanyController#cities()}
     */
    @Test
    void testCities() {
        ArrayList<City> cityList = new ArrayList<>();
        when(this.cityRepository.findAll()).thenReturn(cityList);
        Iterable<City> actualCitiesResult = this.companyController.cities();
        assertSame(cityList, actualCitiesResult);
        assertTrue(((Collection<City>) actualCitiesResult).isEmpty());
        verify(this.cityRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyController#countries()}
     */
    @Test
    void testCountries() {
        ArrayList<Country> countryList = new ArrayList<>();
        when(this.countryRepository.findAll()).thenReturn(countryList);
        Iterable<Country> actualCountriesResult = this.companyController.countries();
        assertSame(countryList, actualCountriesResult);
        assertTrue(((Collection<Country>) actualCountriesResult).isEmpty());
        verify(this.countryRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyController#createCompany(Company, City)}
     */
    @Test
    void testCreateCompany() {
        Company company = new Company();
        company.setAddress("42 Main St");
        company.setCities(new HashSet<>());
        company.setCompanyName("Company Name");
        company.setHomeNum(10);
        company.setId(1);
        when(this.companyRepository.save((Company) any())).thenReturn(company);

        Company company1 = new Company();
        company1.setAddress("42 Main St");
        company1.setCities(new HashSet<>());
        company1.setCompanyName("Company Name");
        company1.setHomeNum(10);
        company1.setId(1);

        City city = new City();
        city.setAtms(new HashSet<>());
        city.setCityName("Oxford");
        city.setId(1);
        assertSame(company, this.companyController.createCompany(company1, city));
        verify(this.companyRepository).save((Company) any());
        assertEquals(1, company1.getCities().size());
    }

    /**
     * Method under test: {@link CompanyController#companies()}
     */
    @Test
    void testCompanies() {
        ArrayList<Company> companyList = new ArrayList<>();
        when(this.companyRepository.findAll()).thenReturn(companyList);
        Iterable<Company> actualCompaniesResult = this.companyController.companies();
        assertSame(companyList, actualCompaniesResult);
        assertTrue(((Collection<Company>) actualCompaniesResult).isEmpty());
        verify(this.companyRepository).findAll();
    }
}

