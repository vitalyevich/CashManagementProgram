package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.service.AtmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequestMapping("/monitoring")
@Controller
public class MonitoringController {

    @Autowired
    private AtmServiceImpl atmService;

    @GetMapping("")
    public String monitoring(Model model) {

        Iterable<Atm> atms = atmService.getAtms();

       /* for (Atm atm : atms) {
            if (atm.getCassettes().iterator().next() == null) {
                atm.setListCassettes("NOT");
                continue;
            }
            while (atm.getCassettes().iterator().hasNext()) {
                    atm.setListCassettes(atm.getListCassettes() +
                            atm.getCassettes().iterator().next().getAmount() + "("+atm.getCassettes().iterator().next().getBanknote()+")" + " ");
                }
            }*/

        model.addAttribute("atms", atms);
        model.addAttribute("headerText", "Мониторинг");
        return "monitoring";
    }

    @GetMapping("/stats")
    public String stats(Model model) {
        return null;
    }
}
