package com.cashmanagement.vitalyevich.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/maps")
    public String maps(Model model) {

        model.addAttribute("headerText", "Карты");
        return "maps";
    }
}
