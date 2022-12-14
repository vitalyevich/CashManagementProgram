package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.City;
import com.cashmanagement.vitalyevich.client.model.Country;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import org.springframework.ui.Model;

import java.util.LinkedList;
import java.util.List;

public class Sidebar {

    public void getDropDown(String url, CompanyServiceImpl companyService, Model model) {
        Iterable<Country> countries = companyService.getCountries();
        List<Atm> atmList = new LinkedList<>();

        for (Country country: countries) {
            model.addAttribute("countryCities", country.getCities().size());
            for (City city: country.getCities()) {
                country.setCityAtms(city.getAtms().size());
                for (Atm atm: city.getAtms()) {
                    atmList.add(atm);
                }
            }
        }
        //
        model.addAttribute("atmList", atmList);
        model.addAttribute("countries", countries);
        model.addAttribute("url", "/monitoring");
    }
}
