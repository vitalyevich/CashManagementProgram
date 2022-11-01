package com.cashmanagement.vitalyevich.client.controller;

import com.cashmanagement.vitalyevich.client.model.User;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("headerText", "Пользователи");

        // test
        Iterable<User> users = userService.getUsers();
        if (users == null) {
            return "error/502";
        }

        int size = IterableUtils.size(users);
        model.addAttribute("users", users);
        model.addAttribute("size", size);
        return "users"; //error/502
    }

    @GetMapping("/registration")
    public String registration(Model model) {

        return "users/registration-user"; //error/502
    }

    @GetMapping("/edit")
    public String edit(Model model) {

        return "users/edit-user"; //error/502
    }
}
