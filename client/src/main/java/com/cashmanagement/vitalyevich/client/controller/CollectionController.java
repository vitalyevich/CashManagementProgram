package com.cashmanagement.vitalyevich.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollectionController {

    @GetMapping("/collection")
    public String collection(Model model) {

        model.addAttribute("headerText", "Инкассации");
        return "collection";
    }
}
