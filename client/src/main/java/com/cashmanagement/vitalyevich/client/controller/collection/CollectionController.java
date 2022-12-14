package com.cashmanagement.vitalyevich.client.controller.collection;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.*;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/collection")
@Controller
public class CollectionController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("")
    public String collection(Model model) {

        Iterable<StorageOrder> storageOrders = orderService.getStorageOrders();

        List<OrderStage> orderStages = (List<OrderStage>) orderService.getOrderStages();

        List<String> nameStages = new ArrayList<>();
        nameStages.add("Генерация заказа наличных денег");
        nameStages.add("Принятие заказа наличных денег");
        nameStages.add("Заполнение кассет");
        nameStages.add("Передача наличных");
        nameStages.add("Инкассация (загрузка)");
        nameStages.add("Инкассация (разгрузка остатков)");
        nameStages.add("Приём наличных в хранилище");

        int count = 0;
        for (OrderStage orderStage: orderStages) {
            orderStage.setStageName(nameStages.get(count));
            count++;
        }


        model.addAttribute("storageOrders", storageOrders);
        model.addAttribute("orderStages", orderStages);

        model.addAttribute("headerText", "Инкассации");
        model.addAttribute("headerPost", "Старший инкассатор " + seance.getUser().getFirstName());
        return "collection";
    }

    @PostMapping("/cancel-order")
    public String cancelOrder(@RequestParam Integer rowId, RedirectAttributes rm) {

        rm.addFlashAttribute("url", "/collection/cancel-order/confirm");
        rm.addFlashAttribute("urlPage", "/collection");
        rm.addFlashAttribute("id", rowId);

        return "redirect:/collection#blackout-confirm";
    }

    @PostMapping("/cancel-order/confirm")
    public String cancelConfirm(@RequestParam Integer rowId) {

        StorageOrder storageOrder = orderService.getStorageOrder(rowId);
        orderService.deleteOrder(storageOrder.getOrder().getId());

        return "redirect:/collection";
    }

    @PostMapping("/confirm-order")
    public String confirmOrder(@RequestParam Integer rowId) {

        BrigadeOrder brigadeOrder = orderService.getBrigadeOrder(rowId);
        brigadeOrder.getOrder().setStage("Принятие заказа наличных денег");

        orderService.updateOrder(brigadeOrder.getOrder(), brigadeOrder.getOrder().getPlan().getId(),
                brigadeOrder.getUser().getId());

        orderService.saveOrderStage(new OrderStage(new OrderStageId(brigadeOrder.getOrder().getId(), 2),
                brigadeOrder.getOrder(), Instant.now()));

        return "redirect:/collection";
    }

    @GetMapping("/stage/{id}")
    public String stageOrder(Model model, @PathVariable String id) {
        return null;
    }
}
