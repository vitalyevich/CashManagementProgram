package com.cashmanagement.vitalyevich.client.controller.collection;

import com.cashmanagement.vitalyevich.client.model.Brigade;
import com.cashmanagement.vitalyevich.client.model.BrigadeOrder;
import com.cashmanagement.vitalyevich.client.model.User;
import com.cashmanagement.vitalyevich.client.service.OrderService;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/planning-collection")
@Controller
public class PlanningCollectionController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public String planningCollection(Model model) {

        Iterable<BrigadeOrder> brigadeOrders = orderService.getBrigadeOrders();

        List<Brigade> brigades = (List<Brigade>) userService.getBrigades();

        String brigadeUsers = "";

        for (Brigade brigade: brigades) {
            brigadeUsers = "";
            for (User user: brigade.getUsers()) {
                brigadeUsers += user.getLastName() + " " + user.getFirstName() + "\n";
            }
            brigade.setBrigadeUsers(brigadeUsers);
        }

        model.addAttribute("brigadeOrders", brigadeOrders);
        model.addAttribute("brigades", brigades);

        model.addAttribute("headerText", "Планирование");
        return "planning-collection";
    }


    @GetMapping("/calculate")
    public String calculate(Model model) {
        return null;
    }

    @GetMapping("/cancel-order")
    public String cancelOrder(Model model) {
        return "redirect:/planning-collection#blackout-confirm";
    }

    @GetMapping("/create-brigade")
    public String createBrigade(Model model) {
        return "redirect:/planning-collection#blackout-brigade";
    }

    @GetMapping("/disband")
    public String disband(Model model) {
        return null;
    }

    @GetMapping("/approve")
    public String approve(Model model) {
        return null;
    }
}
