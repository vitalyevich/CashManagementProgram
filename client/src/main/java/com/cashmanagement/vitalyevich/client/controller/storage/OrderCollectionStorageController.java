package com.cashmanagement.vitalyevich.client.controller.storage;

import com.cashmanagement.vitalyevich.client.model.Order;
import com.cashmanagement.vitalyevich.client.model.Storage;
import com.cashmanagement.vitalyevich.client.service.OrderService;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String cashOrder(Model model) {

        return "/storage/cash-order";
    }
}
