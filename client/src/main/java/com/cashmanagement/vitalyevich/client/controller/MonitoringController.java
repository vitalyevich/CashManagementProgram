package com.cashmanagement.vitalyevich.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MonitoringController {

    @GetMapping("/monitoring")
    public String monitoring(Model model) {

        model.addAttribute("headerText", "Мониторинг");
        return "monitoring";
    }
}
