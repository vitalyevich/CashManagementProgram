package com.cashmanagement.vitalyevich.client.controller;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import com.cashmanagement.vitalyevich.client.service.PlanningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanningController {

    @Autowired
    private PlanningServiceImpl planningService;


    @GetMapping("/planning")
    public String planning(Model model) {

        Iterable<PlanAtm> planAtms = planningService.getPlans();
        if (planAtms == null) {
            return "error/502";
        }

        model.addAttribute("plans", planAtms);
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
