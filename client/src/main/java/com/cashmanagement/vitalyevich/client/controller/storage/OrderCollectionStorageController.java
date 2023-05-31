package com.cashmanagement.vitalyevich.client.controller.storage;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.*;
import com.cashmanagement.vitalyevich.client.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequestMapping("/storage-collection")
@Controller
public class OrderCollectionStorageController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private AtmServiceImpl atmService;

    @Autowired
    private PlanningServiceImpl planningService;

    @Autowired
    private FilterService filterService;

    private List<Order> orders = new LinkedList<>();

    private Integer orderId = 1;

    private Boolean edit = false;
    private Boolean add = false;

    @GetMapping("")
    public String collection(Model model) {

        orders = (List<Order>) orderService.getOrders();

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

/*            orderList.clear();
            Order order = orderService.getOrderById(orderId);
            orderList.add(order);*/
            //int Id = orders.get(orderId-1).getId();

            orders.get(orderId - 1).setMarked("marked");
            model.addAttribute("id", orderId - 1);
            model.addAttribute("text", "Детализация заказа, " + orders.get(0).getPlan().getAtm().getCompany() + ", Заказ наличных");


            model.addAttribute("disabled", false);
            model.addAttribute("marked", "marked");

            List<Cassette> cassettes = new LinkedList<>();

            for (Cassette cassette : orders.get(0).getPlan().getCassettes()) {
                cassette.setSumAmount(cassette.getAmount() * Integer.parseInt(cassette.getBanknote()));
                cassettes.add(cassette);
            }
            model.addAttribute("cassettes", cassettes);

            if (edit == true) {
                int id = orders.get(orderId - 1).getId();
                Order order = orderService.getOrderById(id);
                String date = order.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                model.addAttribute("date", date);
                model.addAttribute("cassettesList", order.getPlan().getCassettes());
                edit = false;
            }
            if (add == true) {

                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                model.addAttribute("date",date);
                add = false;
            }

        } else {
            model.addAttribute("disabled", true);
        }

        List<Company> companies = (List<Company>) companyService.getCompany();
        model.addAttribute("companies", companies);
        model.addAttribute("orders", orders);
        model.addAttribute("url", "/storage-collection/cancel/confirm");

        int id = orderId - 1;
        List<Atm> atms = orders.stream()
                .map(order -> order.getPlan().getAtm())
                .filter(Objects::nonNull) // Filter out null values
                .collect(ArrayList::new, List::add, List::addAll);
        filterService.getValues(model, "/storage-collection", id, atms);

        return "storage-collection";
    }

    @GetMapping("/{id}")
    public String plan(Model model, @PathVariable Integer id) {

        orderId = id;
        return "redirect:/storage-collection";
    }

    @GetMapping("/cash-order")
    public String cashOrder(Model model, RedirectAttributes rm) {

        add = true;
        return "redirect:/storage-collection#blackout-cash";
    }

    @PostMapping("/edit-cash")
    public String editCash(@RequestParam("data") String data) {

        if (!data.equals("") || !data.isEmpty() || !data.equals("  \n")) { // createPlanCassettes

            Order order = orderService.getOrderById(orders.get(orderId - 1).getId());

            List<Cassette> cassetteList = getCassettesList(data);

            for (Cassette cassette: order.getPlan().getCassettes())
            {
                for (Cassette cassette1: cassetteList) {
                    cassette1.setId(cassette.getId());
                    cassette1.setCassetteNum(cassette.getCassetteNum());
                }
            }

            atmService.updateCassettes(cassetteList);

            Set<Cassette> cassetteSet = new HashSet<>(cassetteList);

            PlanAtm planAtm = planningService.getPlan(order.getPlan().getId());
            planAtm.setCassettes(cassetteSet);
            planningService.updatePlanAtm(planAtm);
            orderService.updateOrder(order,order.getPlan().getId(),seance.getUser().getId());
        }

        return "redirect:/storage-collection";
    }


    private List<Cassette> getCassettesList(String tableData) {
        String html = tableData;

        List<Cassette> cassettes = new ArrayList<>();

        String[] rows = tableData.split("\n");

        for (int i = 0; i < rows.length; i++) {
            String rowData = rows[i];

            // Пропускаем первую пустую строку
            if (i == 0 && rowData.trim().isEmpty()) {
                continue;
            }

            // Удаляем символы перевода строки \r
            rowData = rowData.replaceAll("\r", "");

            String[] values = rowData.split(" ");

            if (values.length == 3) {
                String currency = values[0];
                String banknote = values[1];
                String amount = values[2];

                cassettes.add(new Cassette(Double.parseDouble(banknote), currency, Integer.parseInt(amount)));
            }
        }
        return  cassettes;
    }

    @PostMapping("/cash")
    public String cash(@RequestParam("tableData") String tableData, @RequestParam("date") String date) {

        List<Cassette> cassetteList = atmService.saveCassettes(getCassettesList(tableData));

        int i = 1;
        for (Cassette cassette1: cassetteList) {
            cassette1.setCassetteNum(i);
            i++;
        }

        PlanAtm planAtm = planningService.getPlan(12);

        Set<Cassette> cassetteSet = new HashSet<>(cassetteList);

        planAtm.setCassettes(cassetteSet);
        planningService.updatePlanAtm(planAtm);

        Order order = new Order();
        order.setStage("Генерация заказа наличных денег");
        order.setStatus("Новый");

        order.setOrderDate(LocalDate.now());

        orderService.saveOrder(order,planAtm.getId(),seance.getUser().getId());

        return "redirect:/storage-collection";
    }


    @PostMapping("/cash-order/accept")
    public String accept(Model model, RedirectAttributes rm, @RequestParam Integer rowId) {

        return "redirect:/storage-collection";
    }

    @GetMapping("/edit-cash-order")
    public String editCashOrder(Model model, RedirectAttributes rm) {
        edit = true;
        return "redirect:/storage-collection#blackout-edit";
    }

    @PostMapping("/cancel")
    public String cancel(Model model, RedirectAttributes rm, @RequestParam Integer rowId) {

        rm.addFlashAttribute("url", "/storage-collection/cancel/confirm");
        rm.addFlashAttribute("id", rowId);
        return "redirect:/storage-collection#blackout-confirm";
    }

    @PostMapping("/cancel/confirm")
    public String cancelConfirm(Model model, RedirectAttributes rm) {

        int Id = orders.get(orderId-1).getId();
        orderService.deleteByOrder(Id);
        orderId = 1;
        return "redirect:/storage-collection";
    }

    @PostMapping("/confirm")
    public String confirm(Model model, RedirectAttributes rm) {

        int Id = orders.get(orderId-1).getId();
        Order order = orderService.getOrderById(Id);

        order.setStatus("Подтвержден");
        orderService.updateOrder(order, order.getPlan().getId(), order.getUser().getId());
        return "redirect:/storage-collection";
    }

    @PostMapping("/transfer")
    public String transfer(Model model, RedirectAttributes rm) {

        int Id = orders.get(orderId-1).getId();
        Order order = orderService.getOrderById(Id);

        order.setStatus("Передан на исполнение");
        orderService.updateOrder(order, order.getPlan().getId(), order.getUser().getId());

        StorageOrder storageOrder = new StorageOrder();
        storageOrder.setOrderDate(LocalDate.now());
        storageOrder.setUser(seance.getUser());
        storageOrder.setOrder(order);
        orderService.saveStorageOrder(storageOrder);

        return "redirect:/storage-collection";
    }

    @PostMapping("/execute")
    public String execute(Model model, RedirectAttributes rm) {

        int Id = orders.get(orderId-1).getId();
        Order order = orderService.getOrderById(Id);

        if (order == null) {
            return "/error/500";
        }

        order.setStatus("Выполнен");
        orderService.updateOrder(order, order.getPlan().getId(), order.getUser().getId());
        return "redirect:/storage-collection";
    }


}
