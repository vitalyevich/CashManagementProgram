package com.cashmanagement.vitalyevich.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/confirm")
    public String confirm(Model model) {

        return "/confirmations/confirmation-window";
    }
}
