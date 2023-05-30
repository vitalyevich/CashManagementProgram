package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.City;
import com.cashmanagement.vitalyevich.client.model.Country;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Sidebar {

    public void getDropDown(String url, CompanyServiceImpl companyService, Model model) {
        Iterable<Country> countries = companyService.getCountries();
        List<Atm> atmList = new LinkedList<>();
        List<City> cities = new LinkedList<>();

        for (Country country: countries) {

            model.addAttribute("countryCities", country.getCities().size());

            for (City city: country.getCities()) {
                atmList = new LinkedList<>();
                city.setCityAtms(city.getAtms().size());
                cities.add(city);

                for (Atm atm: city.getAtms()) {
                    atmList.add(atm);
                }
                city.setAtmsList(atmList);
                Collections.sort(city.getAtmsList(), compareByAtmUid);
            }
        }
        Collections.sort(cities, compareById);
        //
        model.addAttribute("cities", cities);
        model.addAttribute("urlPage", url);
    }

    Comparator<Atm> compareByAtmUid = new Comparator<Atm>() {
        @Override
        public int compare(Atm o1, Atm o2) {
            return o1.getAtmUid().compareTo(o2.getAtmUid());
        }
    };

    Comparator<City> compareById = new Comparator<City>() {
        @Override
        public int compare(City o1, City o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
}
