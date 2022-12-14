package com.cashmanagement.vitalyevich.client.controller.analytic;

import com.cashmanagement.vitalyevich.client.config.Seance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyticController {

    private Seance seance = Seance.getInstance();

    @GetMapping("/analytics")
    public String analytics(Model model) {
        model.addAttribute("headerPost", "Старший дилер " + seance.getUser().getFirstName());
        model.addAttribute("headerText", "Аналитика");
        return "analytics";
    }
}
