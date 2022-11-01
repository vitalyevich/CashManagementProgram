package com.cashmanagement.vitalyevich.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonitoringStorageController {

    @GetMapping("/monitoring-storage")
    public String monitoring(Model model) {

        model.addAttribute("headerText", "Мониторинг");
        return "monitoring-storage";
    }
}
