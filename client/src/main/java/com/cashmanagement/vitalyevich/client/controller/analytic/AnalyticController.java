package com.cashmanagement.vitalyevich.client.controller.analytic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyticController {

    @GetMapping("/analytics")
    public String analytics(Model model) {
        model.addAttribute("headerText", "Аналитика");
        return "analytics";
    }
}
