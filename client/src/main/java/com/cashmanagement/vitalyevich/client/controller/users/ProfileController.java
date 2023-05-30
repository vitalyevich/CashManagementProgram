package com.cashmanagement.vitalyevich.client.controller.users;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.Access;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ProfileController {

    private Seance seance = Seance.getInstance();

    @GetMapping("/profile-admin")
    public String profileAdmin(Model model) {

        model.addAttribute("headerText", "Профиль");
        model.addAttribute("headerPost", "Руководитель "+seance.getUser().getFirstName());
        model.addAttribute("post", "Руководитель");
        model.addAttribute("company", seance.getUser().getCompanyName());
        model.addAttribute("name", seance.getUser().getFirstName() + " " + seance.getUser().getLastName());
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        //userService.saveWork(new WorkTime(seance.getUser().getFirstName(), seance.getUser().getLastName(), "вход на страницу профиля"));

        return "/profiles/profile-admin";
    }

    @GetMapping("/profile-cashier")
    public String profileCashier(Model model) {

        model.addAttribute("headerText", "Профиль");
        model.addAttribute("headerPost", "Старший кассир " + seance.getUser().getFirstName());
        model.addAttribute("post", "Старший кассир");
        model.addAttribute("company", seance.getUser().getCompanyName());
        model.addAttribute("name", seance.getUser().getFirstName() + " " + seance.getUser().getLastName());
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return "/profiles/profile-cashier";
    }

    @GetMapping("/profile-cashier-storage")
    public String profileCashierStorage(Model model) {

        model.addAttribute("headerText", "Профиль");
        model.addAttribute("headerPost", "Старший кассир хранилища " + seance.getUser().getFirstName());
        model.addAttribute("post", "Старший кассир хранилища");
        model.addAttribute("company", seance.getUser().getCompanyName());
        model.addAttribute("name", seance.getUser().getFirstName() + " " + seance.getUser().getLastName());
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return "/profiles/profile-cashier-storage";
    }

    @GetMapping("/profile-collection")
    public String profileCollection(Model model) {

        model.addAttribute("headerText", "Профиль");
        model.addAttribute("headerPost", "Старший инкассатор " + seance.getUser().getFirstName());
        model.addAttribute("post", "Старший инкассатор");
        model.addAttribute("company", seance.getUser().getCompanyName());
        model.addAttribute("name", seance.getUser().getFirstName() + " " + seance.getUser().getLastName());
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return "/profiles/profile-collection";
    }

    @GetMapping("/profile-dealer")
    public String profileDealer(Model model) {

        model.addAttribute("headerText", "Профиль");
        model.addAttribute("headerPost", "Старший дилер " + seance.getUser().getFirstName());
        model.addAttribute("post", "Старший дилер");
        model.addAttribute("company", seance.getUser().getCompanyName());
        model.addAttribute("name", seance.getUser().getFirstName() + " " + seance.getUser().getLastName());
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return "/profiles/profile-dealer";
    }

    @GetMapping("/edit-password")
    public String password(Model model) {

        return "redirect:/profile-admin#blackout-password";
    }
}
