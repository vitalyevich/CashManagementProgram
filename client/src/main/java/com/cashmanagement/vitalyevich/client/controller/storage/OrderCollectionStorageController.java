package com.cashmanagement.vitalyevich.client.controller.storage;

import com.cashmanagement.vitalyevich.client.model.Order;
import com.cashmanagement.vitalyevich.client.model.Storage;
import com.cashmanagement.vitalyevich.client.service.OrderService;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderCollectionStorageController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/storage-collection")
    public String collection(Model model) {

        Iterable<Order> orders = orderService.getOrders();

        model.addAttribute("orders", orders);

        model.addAttribute("headerText", "Заказ инкассации");
        return "storage-collection";
    }

    @GetMapping("/cash-order")
    public String cashOrder(Model model, RedirectAttributes rm) {
        rm.addFlashAttribute("window","Заказ наличных");
        return "redirect:/storage-collection#blackout-cash";
    }

    @GetMapping("/edit-cash-order")
    public String editCashOrder(Model model, RedirectAttributes rm) {
        rm.addFlashAttribute("window","Редактирование заказа наличных");
        return "redirect:/storage-collection#blackout-cash";
    }
}
