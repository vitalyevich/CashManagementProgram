package com.cashmanagement.vitalyevich.client.controller.storage;

import com.cashmanagement.vitalyevich.client.model.Order;
import com.cashmanagement.vitalyevich.client.model.Storage;
import com.cashmanagement.vitalyevich.client.service.OrderService;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/storage-collection")
@Controller
public class OrderCollectionStorageController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("")
    public String collection(Model model) {

        Iterable<Order> orders = orderService.getOrders();

        model.addAttribute("orders", orders);

        model.addAttribute("headerText", "Заказ инкассации");
        return "storage-collection";
    }

    @GetMapping("/cash-order")
    public String cashOrder(Model model, RedirectAttributes rm) {
        return "redirect:/storage-collection#blackout-cash";
    }

    @GetMapping("/edit-cash-order")
    public String editCashOrder(Model model, RedirectAttributes rm) {
        return "redirect:/storage-collection#blackout-edit";
    }

    @GetMapping("/cancel")
    public String cancel(Model model, RedirectAttributes rm) {
        return "redirect:/storage-collection#blackout-confirm";
    }

    @GetMapping("/confirm")
    public String confirm(Model model, RedirectAttributes rm) {
        return null;
    }

    @GetMapping("/transfer")
    public String transfer(Model model, RedirectAttributes rm) {
        return null;
    }

    @GetMapping("/execute")
    public String execute(Model model, RedirectAttributes rm) {
        return null;
    }
}
