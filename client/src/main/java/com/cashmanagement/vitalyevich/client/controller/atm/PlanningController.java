package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.*;
import com.cashmanagement.vitalyevich.client.service.AtmServiceImpl;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import com.cashmanagement.vitalyevich.client.service.OrderServiceImpl;
import com.cashmanagement.vitalyevich.client.service.PlanningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequestMapping("/planning")
@Controller
public class PlanningController {


    private Seance seance = Seance.getInstance();

    @Autowired
    private PlanningServiceImpl planningService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private AtmServiceImpl atmService;

    @GetMapping("")
    public String planning(Model model) {

        List<PlanAtm> planAtms = (List<PlanAtm>) planningService.getPlans();


        for (PlanAtm planAtm : planAtms) {

            Order order = orderService.getOrder(planAtm.getId());
            planAtm.setOrder(order);

            planAtm.setListCassettes("");
            planAtm.setAmount(0);
            if (!planAtm.getCassettes().iterator().hasNext()) {
                planAtm.setListCassettes(" ");
            }
            for (Cassette cassette: planAtm.getCassettes())
            {
                planAtm.setListCassettes(planAtm.getListCassettes() +
                        cassette.getAmount() + " ("+cassette.getBanknote()+")" + ", ");
                planAtm.setAmount(planAtm.getAmount() + cassette.getAmount());
            }
        }

        if (planId != null) {
            planAtms.get(planId-1).setMarked("marked");
            model.addAttribute("id", planId-1);
            PlanAtm planAtm = planningService.getPlan(planId);
            Order order = orderService.getOrder(planAtm.getId());
            planAtm.setOrder(order);
            model.addAttribute("text", planAtm.getAtm().getAtmUid()+", План. инкассация " + planAtm.getDate());
            model.addAttribute("disabled", false);
            model.addAttribute("marked", "marked");
        } else {
            model.addAttribute("disabled", true);
        }

        if (forecast == false) {
            if (atmId != null) {
                PlanAtm planAtm = planningService.getPlan(atmId);
                int sum = 0;
                for (Cassette cassette : planAtm.getAtm().getCassettes()) {
                    cassette.setSumAmount(cassette.getAmount() * Integer.parseInt(cassette.getBanknote()));
                    sum += cassette.getSumAmount();
                }
                model.addAttribute("cassettes", planAtm.getAtm().getCassettes());
                model.addAttribute("sum", sum);
            }
        } else {
            int sum = 0;
            for (Cassette cassette : cassetteList1) {
                cassette.setSumAmount(cassette.getAmount() * Integer.parseInt(cassette.getBanknote()));
                sum += cassette.getSumAmount();
            }
            model.addAttribute("cassettes", cassetteList1);
            model.addAttribute("sum", sum);
            forecast = false;
        }

        model.addAttribute("plans", planAtms);
        model.addAttribute("headerText", "Планирование");
        model.addAttribute("headerPost", "Старший кассир " + seance.getUser().getFirstName());
        model.addAttribute("plan", planAtmArrayList);

        Sidebar sidebar = new Sidebar();
        sidebar.getDropDown("/planning", companyService, model);

        return "planning";
    }

    private List<Atm> atms = new LinkedList<>();

    @GetMapping("/plan-cash")
    public String planCash(@RequestParam Integer rowId, Model model, RedirectAttributes rm) {

        atmId = rowId + 1;
        return "redirect:/planning#blackout-plan";
    }

    private List<Cassette> cassetteList = new LinkedList<>();
    private List<Cassette> cassetteList1 = new LinkedList<>();
    private boolean forecast = false;

    @PostMapping("/plan-cash-funct")
    public String funct(@RequestParam Integer period, @RequestParam String date, @RequestParam Integer type, Model model, RedirectAttributes rm) {
        cassetteList.clear();

        forecast = true;


        return "redirect:/planning#blackout-plan";
    }

    @GetMapping("/add")
    public String add() {

        PlanAtm planAtm = planningService.getPlan(atmId);
        Set<Cassette> cassettes = new LinkedHashSet<>();
        for (Cassette cassette: cassetteList) {
            cassettes.add(cassette);
        }
        planAtm.setCassettes(cassettes);
        //planningService.updatePlanAtm(planAtm);
        return "redirect:/planning";
    }

    @PostMapping("/plan-cash/accept")
    public String plan(@RequestParam Integer rowId, Model model, RedirectAttributes rm) {

        PlanAtm planAtm = planningService.getPlan(rowId);
        planAtm.setStatus("Рассчитан");

        planningService.updatePlanAtm(planAtm);

        return "redirect:/planning";
    }

    @PostMapping("/plan-cash-function")
    public String function(@RequestParam Integer period, @RequestParam String date, @RequestParam Integer type, Model model, RedirectAttributes rm) {
        cassetteList1 = new LinkedList<>();
        forecast = true;

        PlanAtm planAtm = planningService.getPlan(atmId);

        if (planAtm == null) {
            return "/error/500";
        }

        for (Cassette cassette :planAtm.getCassettes()) {
            int sum = cassette.getAmount() + 329;
            cassette.setAmount(sum);
            cassetteList1.add(cassette);
        }

        return "redirect:/planning#blackout-plan";
    }

    @GetMapping("/edit-plan-cash")
    public String editPlanCash(@RequestParam Integer rowId, Model model, RedirectAttributes rm) {

        //PlanAtm planAtm = planningService.getPlan(rowId);

        return "redirect:/planning#blackout-edit";
    }

    @GetMapping("/edit-plan-cash/accept")
    public String edit(Model model, RedirectAttributes rm) {

       /* PlanAtm planAtm = planningService.getPlan(rowId);
        planAtm.setStatus("Принят");

        planningService.updatePlanAtm(planAtm);*/
        return "redirect:/planning";
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam Integer rowId, RedirectAttributes rm) {

        List<PlanAtm> planAtms = (List<PlanAtm>) planningService.getPlans();
        if (planAtms == null) {
            return "/error/500";
        }

        rm.addFlashAttribute("url", "/planning/create-order/confirm");
        rm.addFlashAttribute("urlPage", "/planning");
        rm.addFlashAttribute("id", rowId);
        return "redirect:/planning#blackout-confirm";
    }

    @PostMapping("/create-order/confirm")
    public String create(@RequestParam Integer rowId, RedirectAttributes rm) {

        PlanAtm planAtm = planningService.getPlan(rowId);
        planAtm.setStatus("Передан на исполнение");
        planningService.updatePlanAtm(planAtm);

        Order order = new Order();
        order.setStage("Генерация заказа наличных денег");
        order.setStatus("Новый");
        order.setOrderDate(LocalDate.now());

        orderService.saveOrder(order, planAtm.getId(), planAtm.getUser().getId());

        //order.setOrderStages();

        return "redirect:/planning";
    }

    @PostMapping("/accept")
    public String accept(Model model, @RequestParam Integer rowId) {

        PlanAtm planAtm = planningService.getPlan(rowId);
        planAtm.setStatus("Принят");

        planningService.updatePlanAtm(planAtm);

        return "redirect:/planning";
    }

    private List<PlanAtm> planAtmArrayList = new LinkedList<>();

    private Integer planId = null;
    private Integer atmId = null;

    @GetMapping("/{id}")
    public String plan(Model model, @PathVariable Integer id) {

        planId = id;

        planAtmArrayList.clear();

        PlanAtm planAtm = planningService.getPlan(id);
        planAtm.setParameter("ID объекта");
        planAtm.setValue(planAtm.getAtm().getAtmUid());
        planAtmArrayList.add(planAtm);

        planAtm = planningService.getPlan(id);
        planAtm.setParameter("Заказ");
        planAtmArrayList.add(planAtm);

        planAtm = planningService.getPlan(id);
        Order order = orderService.getOrder(planAtm.getId());
        planAtm.setParameter("Дата инкассации");
        try {
            planAtm.setValue(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
        } catch (NullPointerException e) {
        }
        planAtmArrayList.add(planAtm);

        planAtm = planningService.getPlan(id);
        planAtm.setParameter("Период");
        planAtm.setValue(planAtm.getPlanPeriod().toString());
        planAtmArrayList.add(planAtm);

        planAtm = planningService.getPlan(id);
        planAtm.setParameter("Статус");
        planAtm.setValue(planAtm.getStatus());
        planAtmArrayList.add(planAtm);

        planAtm = planningService.getPlan(id);
        planAtm.setParameter("Тип инкассации");
        planAtm.setValue("План");
        planAtmArrayList.add(planAtm);

        int sum = 0;
        for (Cassette cassette: planAtm.getCassettes())
        {
            planAtm = planningService.getPlan(id);
            if (sum == 0) {
                planAtm.setParameter("Валюта");
            }
            planAtm.setValue(cassette.getCurrency());
            planAtm.setBanknote(cassette.getBanknote());
            planAtm.setAmountCassette(cassette.getAmount().toString());
            planAtm.setSum(cassette.getAmount() * Integer.parseInt(cassette.getBanknote()));
            sum += planAtm.getSum();
            planAtmArrayList.add(planAtm);
        }

        planAtm = planningService.getPlan(id);
        planAtm.setParameter("Алгоритм");
        planAtm.setAmountCassette(null);
        planAtm.setBanknote(null);
        planAtm.setValue(planAtm.getPlanMethod());
        planAtmArrayList.add(planAtm);

        planAtm = planningService.getPlan(id);
        planAtm.setParameter("Всего");
        planAtm.setAmountCassette(null);
        planAtm.setBanknote(null);
        planAtm.setValue(planAtm.getCurrency());
        planAtm.setSum(sum);
        planAtmArrayList.add(planAtm);


        return "redirect:/planning";
    }

}
