package com.cashmanagement.vitalyevich.client.controller.analytic;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.controller.atm.Sidebar;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyticController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping("/analytics")
    public String analytics(Model model) {
        model.addAttribute("headerPost", "Старший дилер " + seance.getUser().getFirstName());
        model.addAttribute("headerText", "Аналитика");

        Sidebar sidebar = new Sidebar();
        sidebar.getDropDown("/collection", companyService, model);

        return "analytics";
    }
}
