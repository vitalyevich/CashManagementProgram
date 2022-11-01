package com.cashmanagement.vitalyevich.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanningController {

    @GetMapping("/planning")
    public String planning(Model model) {

        model.addAttribute("headerText", "Планирование");
        return "planning";
    }

    @GetMapping("/plan-cash")
    public String planCash(Model model) {
        model.addAttribute("window","План наличных");
        return "/planning/plan-cash";
    }
    @GetMapping("/edit-plan-cash")
    public String editPlanCash(Model model) {
        model.addAttribute("window","Редактирование плана наличных");
        return "/planning/plan-cash";
    }

    @GetMapping("/planning-collection")
    public String planningCollection(Model model) {

        model.addAttribute("headerText", "Планирование (CIT)");
        return "planning-collection";
    }

    @GetMapping("/create-brigade")
    public String createBrigade(Model model) {

        return "planning/create-brigade";
    }
}
