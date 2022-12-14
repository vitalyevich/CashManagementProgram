package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.Cassette;
import com.cashmanagement.vitalyevich.client.service.AtmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@RequestMapping("/monitoring")
@Controller
public class MonitoringController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private AtmServiceImpl atmService;

    @GetMapping("")
    public String monitoring(Model model) {

        Iterable<Atm> atms = atmService.getAtms();

        for (Atm atm : atms) {
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
        return "monitoring";
    }

    @GetMapping("/stats")
    public String stats(Model model) {
        return null;
    }
}
