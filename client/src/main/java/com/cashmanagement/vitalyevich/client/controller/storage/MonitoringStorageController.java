package com.cashmanagement.vitalyevich.client.controller.storage;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.Storage;
import com.cashmanagement.vitalyevich.client.model.StorageOperation;
import com.cashmanagement.vitalyevich.client.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonitoringStorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/monitoring-storage")
    public String monitoring(Model model) {

        Iterable<Storage> storages = storageService.getStorages();

        Iterable<StorageOperation> operations = storageService.getStorageOperations();

        model.addAttribute("operations", operations);
        model.addAttribute("storages", storages);

        model.addAttribute("headerText", "Мониторинг");
        return "monitoring-storage";
    }

    @GetMapping("/monitoring-storages")
    public String monitoringStorage(Model model) {

        Iterable<StorageOperation> operations = storageService.getStorageOperations();
        if (operations == null) {
            return "error/502";
        }

        model.addAttribute("operations", operations);

        model.addAttribute("headerText", "Мониторинг");
        return "monitoring-storages";
    }

    @GetMapping("/balance-storage")
    public String balanceStorage(Model model) {

        return "redirect:monitoring-storage#blackout-balance";
    }

    @GetMapping("/check-balance-storage")
    public String checkBalanceStorage(Model model) {

        return "/storage/check-balance-storage";
    }
}
