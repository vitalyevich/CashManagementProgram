package com.cashmanagement.vitalyevich.client.controller.users;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.Access;
import com.cashmanagement.vitalyevich.client.model.User;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/authorization")
    public String authorization(Model model) {
        return "authorization";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Access access = userService.getAccessByLogin(login);
        seance.setUser(access.getUser());

        if (access.getUser().getNameRole().equals("Руководитель")) {
            return "redirect:/profile-admin";
        }
        else if (access.getUser().getNameRole().equals("Старший дилер")) {
            return "redirect:/profile-dealer";
        }
        else if (access.getUser().getNameRole().equals("Старший кассир")) {
            return "redirect:/profile-cashier";
        }
        else if (access.getUser().getNameRole().equals("Старший кассир хранилища")) {
            return "redirect:/profile-cashier-storage";
        }
        else {
            return "redirect:/profile-collection";
        }
    }
}
