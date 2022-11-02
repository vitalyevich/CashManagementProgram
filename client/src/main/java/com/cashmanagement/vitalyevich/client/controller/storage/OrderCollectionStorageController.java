package com.cashmanagement.vitalyevich.client.controller.storage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderCollectionStorageController {

    @GetMapping("/storage-collection")
    public String collection(Model model) {

        model.addAttribute("headerText", "Заказ инкассации");
        return "storage-collection";
    }

    @GetMapping("/cash-order")
    public String cashOrder(Model model) {

        return "/storage/cash-order";
    }
}
