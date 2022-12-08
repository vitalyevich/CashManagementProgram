package com.cashmanagement.vitalyevich.client.controller.collection;

import com.cashmanagement.vitalyevich.client.model.OrderStage;
import com.cashmanagement.vitalyevich.client.model.StorageOrder;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CollectionController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/collection")
    public String collection(Model model) {

        Iterable<StorageOrder> storageOrders = orderService.getStorageOrders();

        List<OrderStage> orderStages = (List<OrderStage>) orderService.getOrderStages();

        orderStages.get(0).setStageName("Генерация заказа наличных денег");
        orderStages.get(1).setStageName("Принятие заказа наличных денег");
        orderStages.get(2).setStageName("Заполнение кассет");
        orderStages.get(3).setStageName("Передача наличных");
        orderStages.get(4).setStageName("Инкассация (загрузка)");
        orderStages.get(5).setStageName("Инкассация (разгрузка остатков)");
        orderStages.get(6).setStageName("Приём наличных в хранилище");

        model.addAttribute("storageOrders", storageOrders);
        model.addAttribute("orderStages", orderStages);

        model.addAttribute("headerText", "Инкассации");
        return "collection";
    }
}
