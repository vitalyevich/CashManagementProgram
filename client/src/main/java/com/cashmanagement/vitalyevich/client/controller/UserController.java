package com.cashmanagement.vitalyevich.client.controller;

import com.cashmanagement.vitalyevich.client.model.Company;
import com.cashmanagement.vitalyevich.client.model.Role;
import com.cashmanagement.vitalyevich.client.model.User;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("headerText", "Пользователи");

        // test
        Iterable<User> users = userService.getUsers();
        Iterable<Role> roles = userService.getRoles();
        Iterable<Company> companies = userService.getCompany();

        if (users == null || roles == null) {
            return "error/502";
        }

        int size = IterableUtils.size(users);
        model.addAttribute("users", users);
        model.addAttribute("roles",roles);
        model.addAttribute("companies",companies);
        model.addAttribute("size", size);
        return "users"; //error/502
    }

    @GetMapping("/registration")
    public String registration(Model model, RedirectAttributes rm) {

        rm.addFlashAttribute("name","Регистрация пользователя");
        return "redirect:/users#blackout-registration"; //error/502
    }

    //@PostMapping("/registration")
   /* public String addUser(Access accessForm, User userForm, Model model) {
  *//*      Access userFromDb = accessRepository.findByPhone(accessForm.getPhone());

        if (userFromDb != null) {
            rm.addFlashAttribute("message", "Данный номер телефона уже используется!");

            return "redirect:/menu/rolls#blackout-registration";
        }

        if (!accessForm.getPassword().equals(accessForm.getConfirmPassword())) {
            return "redirect:/menu/rolls#blackout-registration";
        }

        accessForm.setActive(true);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1));
        accessForm.setRoles(roles);

        // point create
        String generatedCode = RandomStringUtils.randomAlphanumeric(6);
        userForm.setUserCode(generatedCode.toUpperCase(Locale.ROOT));

        if (userForm.getInviteCode().equals("")) {
            userForm.setInviteCode(null);
        }
        if (userForm.getEmail().equals("")) {
            userForm.setEmail(null);
        }

        userRepository.save(userForm);

        //
        accessForm.setId(userForm.getId());
        accessForm.setUser(new User(userForm.getId()));
        accessRepository.save(accessForm);

        userPointRepository.save(new UserPoint(userForm.getId(),0));
//
        return "redirect:#blackout-authorization";*//*
        return "";
    }*/

    @GetMapping("/edit")
    public String edit(Model model, RedirectAttributes rm) {
        rm.addFlashAttribute("name","Редактирование пользователя");
        return "redirect:/users#blackout-registration"; //error/502
    }

    @PostMapping("/delete")
    public String delete(Model model) {
        return "users";
    }
}
