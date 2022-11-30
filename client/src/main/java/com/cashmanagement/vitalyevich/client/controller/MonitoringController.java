package com.cashmanagement.vitalyevich.client.controller;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.service.AtmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MonitoringController {

    @Autowired
    private AtmServiceImpl atmService;

    @GetMapping("/monitoring")
    public String monitoring(Model model) {

        Iterable<Atm> atms = atmService.getAtms();
        if (atms == null) {
            return "error/502";
        }

        model.addAttribute("atms", atms);
        model.addAttribute("headerText", "Мониторинг");
        return "monitoring";
    }
}
