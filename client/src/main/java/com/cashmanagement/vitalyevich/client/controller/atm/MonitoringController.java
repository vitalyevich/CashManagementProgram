package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.*;
import com.cashmanagement.vitalyevich.client.service.AtmServiceImpl;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@RequestMapping("/monitoring")
@Controller
public class MonitoringController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private AtmServiceImpl atmService;

    @GetMapping("")
    public String monitoring(Model model) {

        Iterable<Atm> atms = atmService.getAtms();
        if (atms == null) {
            return "/error/500";
        }

        for (Atm atm : atms) {
            atm.setDay((int)Math.floor(Math.random() * 24) + 0);
            atm.setListCassettes("");
            atm.setAmount(0);
            if (!atm.getCassettes().iterator().hasNext()) {
                atm.setListCassettes(" ");
            }
            for (Cassette cassette: atm.getCassettes())
            {
                atm.setListCassettes(atm.getListCassettes() +
                        cassette.getAmount() + " ("+cassette.getBanknote()+")" + ", ");
                atm.setAmount(atm.getAmount() + cassette.getAmount());
            }
        }

        model.addAttribute("atms", atms);
        model.addAttribute("headerText", "Мониторинг");
        model.addAttribute("headerPost", "Старший кассир " + seance.getUser().getFirstName());


        Sidebar sidebar = new Sidebar();
        sidebar.getDropDown("/monitoring", companyService, model);

        return "monitoring";
    }

    @GetMapping("/{id}")
    public String atmMonitoring(Model model, @PathVariable String id) {
        return "monitoring";
    }

    @GetMapping("/stats")
    public String stats(Model model) {
        return null;
    }
}
