package com.cashmanagement.vitalyevich.client.controller.collection;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.controller.atm.Sidebar;
import com.cashmanagement.vitalyevich.client.model.*;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@RequestMapping("/collection")
@Controller
public class CollectionController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CompanyServiceImpl companyService;

    Integer orderId = 1;

    private List<OrderStage> orderStages = new LinkedList<>();

    private List<StorageOrder> storageOrders = new LinkedList<>();


    @GetMapping("")
    public String collection(Model model) {

        storageOrders = new LinkedList<>();
        storageOrders = (List<StorageOrder>) orderService.getStorageOrders();

        if (storageOrders == null) {
            return "/error/500";
        }

        if (orderId != null) {

            generateOrderStagesByStorageOrder();

            storageOrders.get(orderId-1).setMarked("marked");
            model.addAttribute("id", orderId-1);

            int Id = storageOrders.get(orderId-1).getId();
            StorageOrder storageOrder = orderService.getStorageOrder(Id);
            model.addAttribute("text", storageOrder.getOrder().getPlan().getAtm().getAtmUid()+", План. инкассация " + storageOrder.getDate());

            if (orderStages.size() >= 1 || storageOrder.getOrder().getStatus().equals("Не определен")) {

                model.addAttribute("stage", orderStages.size());
                model.addAttribute("disabled", true);
            }
            else {
                model.addAttribute("marked", "marked");
                model.addAttribute("disabled", false);
            }
            model.addAttribute("orderStages", orderStages);
        } else {
            model.addAttribute("disabled", true);
        }


        model.addAttribute("storageOrders", storageOrders);

        model.addAttribute("headerText", "Инкассации");
        model.addAttribute("headerPost", "Старший инкассатор " + seance.getUser().getFirstName());

        Sidebar sidebar = new Sidebar();
        sidebar.getDropDown("/collection", companyService, model);

        model.addAttribute("url", "/collection/cancel-order/confirm");

        return "collection";
    }

    private void generateOrderStagesByStorageOrder() {
        int Id = storageOrders.get(orderId-1).getId();
        orderStages = new LinkedList<>();

        orderStages = (List<OrderStage>) orderService.getOrderStage(Id);

        List<String> nameStages = new ArrayList<>();
        nameStages.add("Генерация заказа наличных денег");
        nameStages.add("Принятие заказа наличных денег");
        nameStages.add("Заполнение кассет");
        nameStages.add("Передача наличных");
        nameStages.add("Инкассация (загрузка)");
        nameStages.add("Инкассация (разгрузка остатков)");
        nameStages.add("Приём наличных в хранилище");


        for (OrderStage orderStage: orderStages) {
            orderStage.setStageName(nameStages.get(orderStage.getId().getStageId()-1));
        }
    }

    @GetMapping("/{id}")
    public String atmCollection(Model model, @PathVariable Integer id) {

        orderId = id;

        return "redirect:/collection";
    }

    @GetMapping("/cancel-order")
    public String cancelOrder(Model model, RedirectAttributes rm) {

        rm.addFlashAttribute("url", "/collection/cancel-order/confirm");
        rm.addFlashAttribute("id", orderId);

        return "redirect:/collection#blackout-confirm";
    }

    @PostMapping("/cancel-order/confirm")
    public String cancelConfirm(@RequestParam Integer rowId) {

        int Id = storageOrders.get(orderId-1).getId();

        StorageOrder storageOrder = orderService.getStorageOrder(Id);

        if (storageOrder == null) {
            return "/error/500";
        }

        storageOrder.getOrder().setStatus("Не определен");

        orderService.updateOrder(storageOrder.getOrder(), storageOrder.getOrder().getPlan().getId(), storageOrder.getUser().getId());

        return "redirect:/collection";
    }

    @GetMapping("/confirm-order")
    public String confirmOrder(Model model) {

        int Id = storageOrders.get(orderId-1).getId();

        StorageOrder storageOrder = orderService.getStorageOrder(Id);

        BrigadeOrder brigadeOrder = new BrigadeOrder();
        brigadeOrder.setBrigade(null);
        brigadeOrder.setOrder(storageOrder.getOrder());
        brigadeOrder.setOrderDate(LocalDate.now());
        brigadeOrder.setUser(seance.getUser());

        orderService.saveBrigadeOrder(brigadeOrder);

        brigadeOrder.getOrder().setStage("Генерация заказа наличных денег");

        orderService.updateOrder(storageOrder.getOrder(), storageOrder.getOrder().getPlan().getId(),
                storageOrder.getUser().getId());

        orderService.saveOrderStage(new OrderStage(new OrderStageId(storageOrder.getOrder().getId(), 1),
                storageOrder.getOrder(), Instant.now()));

        return "redirect:/collection";
    }

    @GetMapping("/stage/{id}")
    public String stageOrder(Model model, @PathVariable String id) {
        return null;
    }
}
