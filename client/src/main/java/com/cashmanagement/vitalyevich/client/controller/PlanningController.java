package com.cashmanagement.vitalyevich.client.controller;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import com.cashmanagement.vitalyevich.client.service.PlanningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String planCash(Model model, RedirectAttributes rm) {
        rm.addFlashAttribute("window","План наличных");
        return "redirect:/planning#blackout-plan";
    }
    @GetMapping("/edit-plan-cash")
    public String editPlanCash(Model model, RedirectAttributes rm) {
        rm.addFlashAttribute("window","Редактирование плана наличных");
        return "redirect:/planning#blackout-plan";
    }
}
