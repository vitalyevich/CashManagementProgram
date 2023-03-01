package com.cashmanagement.vitalyevich.client.controller.storage;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.*;
import com.cashmanagement.vitalyevich.client.service.OrderService;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/storage-collection")
@Controller
public class OrderCollectionStorageController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private OrderServiceImpl orderService;

    private List<Order> orderList = new LinkedList<>();

    private Integer orderId = null;

    @GetMapping("")
    public String collection(Model model) {

        List<Order> orders = (List<Order>) orderService.getOrders();

        if (orders == null) {
            return "/error/500";
        }

        for (Order order: orders) {
            for (Cassette cassette: order.getPlan().getCassettes()) {
                order.setAmount(cassette.getAmount() * Integer.parseInt(cassette.getBanknote()));
            }
        }

        model.addAttribute("headerText", "Заказ инкассации");
        model.addAttribute("headerPost", "Старший кассир хранилища " + seance.getUser().getFirstName());

        if (orderId != null) {
            orders.get(orderId-1).setMarked("marked");
            model.addAttribute("id", orderId-1);
            model.addAttribute("text", orders.get(0).getPlan().getAtm().getCompany()+", Заказ наличных");
            model.addAttribute("disabled", false);
            model.addAttribute("marked", "marked");

            List<Cassette> cassettes = new LinkedList<>();

            for (Cassette cassette: orders.get(0).getPlan().getCassettes()) {
                cassette.setSumAmount(cassette.getAmount() * Integer.parseInt(cassette.getBanknote()));
                cassettes.add(cassette);
            }
            model.addAttribute("cassettes", cassettes);

        } else {
            model.addAttribute("disabled", true);
        }

        model.addAttribute("orders", orders);


        return "storage-collection";
    }

    @GetMapping("/{id}")
    public String plan(Model model, @PathVariable Integer id) {

        orderId = id;
        orderList.clear();
        Order order = orderService.getOrderById(id);
        orderList.add(order);
        return "redirect:/storage-collection";
    }

    @GetMapping("/cash-order")
    public String cashOrder(Model model, RedirectAttributes rm) {
        return "redirect:/storage-collection#blackout-cash";
    }

    @GetMapping("/edit-cash-order")
    public String editCashOrder(Model model, RedirectAttributes rm) {
        return "redirect:/storage-collection#blackout-edit";
    }

    @PostMapping("/cancel")
    public String cancel(Model model, RedirectAttributes rm, @RequestParam Integer rowId) {

        rm.addFlashAttribute("url", "/storage-collection/cancel/confirm");
        rm.addFlashAttribute("urlPage", "/storage-collection");
        rm.addFlashAttribute("id", rowId);
        return "redirect:/storage-collection#blackout-confirm";
    }

    @PostMapping("/cancel/confirm")
    public String cancelConfirm(Model model, RedirectAttributes rm, @RequestParam Integer rowId) {

        orderService.deleteByOrder(rowId);
        return "redirect:/storage-collection";
    }

    @PostMapping("/confirm")
    public String confirm(Model model, RedirectAttributes rm, @RequestParam Integer rowId) {

        Order order = orderService.getOrderById(rowId);
        order.setStatus("Подтвержден");
        orderService.updateOrder(order, order.getPlan().getId(), order.getUser().getId());
        return "redirect:/storage-collection";
    }

    @PostMapping("/transfer")
    public String transfer(Model model, RedirectAttributes rm, @RequestParam Integer rowId) {
        Order order = orderService.getOrderById(rowId);
        order.setStatus("Передан на исполнение");
        orderService.updateOrder(order, order.getPlan().getId(), order.getUser().getId());

        StorageOrder storageOrder = new StorageOrder();
        storageOrder.setOrderDate(LocalDate.now());
        storageOrder.setUser(seance.getUser());
        orderService.saveStorageOrder(storageOrder);

        return "redirect:/storage-collection";
    }

    @PostMapping("/execute")
    public String execute(Model model, RedirectAttributes rm, @RequestParam Integer rowId) {
        Order order = orderService.getOrderById(rowId);

        if (order == null) {
            return "/error/500";
        }

        order.setStatus("Выполнен");
        orderService.updateOrder(order, order.getPlan().getId(), order.getUser().getId());
        return "redirect:/storage-collection";
    }


}
