package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import com.cashmanagement.vitalyevich.client.service.PlanningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/planning")
@Controller
public class PlanningController {

    @Autowired
    private PlanningServiceImpl planningService;


    @GetMapping("")
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
        return "redirect:/planning#blackout-plan";
    }
    @GetMapping("/edit-plan-cash")
    public String editPlanCash(Model model, RedirectAttributes rm) {
        return "redirect:/planning#blackout-edit";
    }

    @GetMapping("/create-order")
    public String createOrder(Model model, RedirectAttributes rm) {
        return "redirect:/planning#blackout-confirm";
    }

    @GetMapping("/accept")
    public String accept(Model model, RedirectAttributes rm) {
        return null;
    }

}
