package com.cashmanagement.vitalyevich.client.controller;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.User;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/authorization")
    public String authorization(Model model) {

        User user = userService.getUser(1);

        seance.setUser(user);

        return "authorization";
    }
}
