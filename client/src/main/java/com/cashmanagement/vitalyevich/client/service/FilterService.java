package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.service.AtmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class FilterService {

    @Autowired
    private AtmServiceImpl atmService;

    public void getValues(Model model, String url, Integer atmId, List<Atm> atmListSelect)  {

        if (atmListSelect == null) {
            return;
        }

        model.addAttribute("atmListSelect",atmListSelect);
        model.addAttribute("urlSelect",url);
        model.addAttribute("selectedOptionValue",atmId);
    }
}
