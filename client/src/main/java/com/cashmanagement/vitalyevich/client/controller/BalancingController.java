package com.cashmanagement.vitalyevich.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BalancingController {

    @GetMapping("/balancing")
    public String balancing(Model model) {

        model.addAttribute("headerText", "Балансировка");
        return "balancing";
    }
}
