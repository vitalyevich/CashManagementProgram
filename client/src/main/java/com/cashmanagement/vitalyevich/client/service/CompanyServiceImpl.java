package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<Company> getCompany() {
        String document = """
                     query {
                             companies {
                             id
                             companyName
                             }
                         }
                """;

        try {
            Iterable<Company> companies = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("companies")
                    .toEntity(Company[].class).block()));
            return companies;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Company saveCompany(Company company, Integer cityId) {
        String document = "mutation {\n" +
                "createCompany(company: {\n" +
                "            companyName: \""+company.getCompanyName()+"\"\n" +
                "            address: \""+company.getAddress()+"\",\n" +
                "            homeNum: "+company.getHomeNum()+"\n" +
                "        },\n" +
                "        city: {\n" +
                "            id: "+cityId+"\n" +
                "        }){\n" +
                "            id\n" +
                "        }\n" +
                "}";

        try {
            Company company1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("createCompany")
                    .toEntity(Company.class).block());
            return company1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<Country> getCountries() {
        String document = """
                      query {
                       countries {
                           id,
                           countryName
                           cities { 
                           id,
                           cityName,
                           atms {
                           id,
                           atmUid,
                           }
                           }
                           }
                       }
                """;

        try {
            Iterable<Country> countries = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("countries")
                    .toEntity(Country[].class).block()));
            return countries;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<City> getCities() {
        String document = """
                      query {
                       cities {
                           id,
                           cityName
                           }
                       }
                """;

        try {
            Iterable<City> cities = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("cities")
                    .toEntity(City[].class).block()));
            return cities;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
