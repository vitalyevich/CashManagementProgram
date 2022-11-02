package com.cashmanagement.vitalyevich.client.controller.storage;

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

    @GetMapping("/monitoring-storages")
    public String monitoringStorage(Model model) {

        model.addAttribute("headerText", "Мониторинг");
        return "monitoring-storages";
    }

    @GetMapping("/balance-storage")
    public String balanceStorage(Model model) {

        return "/storage/balance-storage";
    }

    @GetMapping("/check-balance-storage")
    public String checkBalanceStorage(Model model) {

        return "/storage/check-balance-storage";
    }
}
