package com.cashmanagement.vitalyevich.client.controller.analytic;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.controller.atm.Sidebar;
import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.WithdrawalCash;
import com.cashmanagement.vitalyevich.client.service.AtmServiceImpl;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import com.cashmanagement.vitalyevich.client.service.WithdrawalCashServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/analytics")
@Controller
public class AnalyticController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private AtmServiceImpl atmService;

    @Autowired
    private WithdrawalCashServiceImpl withdrawalCashService;

    @GetMapping("")
    public String analytics(Model model) {
        model.addAttribute("headerPost", "Старший дилер " + seance.getUser().getFirstName());
        model.addAttribute("headerText", "Аналитика");

        List<Atm> atmList = (List<Atm>) atmService.getAtms();

        if (atmList == null) {
            return "/error/500";
        }

        long normalCount = 0;
        long problemCount = 0;
        long uncertainCount = 0;

        int total5Amount = 0;
        int total10Amount = 0;
        int total20Amount = 0;
        int total50Amount = 0;
        int total100Amount = 0;
        int total200Amount = 0;

        if (atmId == null) {

            normalCount = atmList.stream()
                    .filter(atm -> atm.getAtmState().equals("Нормальный"))
                    .count();

            problemCount = atmList.stream()
                    .filter(atm -> atm.getAtmState().equals("Проблематичный"))
                    .count();

            uncertainCount = atmList.stream()
                    .filter(atm -> atm.getAtmState().equals("Неопределённый"))
                    .count();

            total5Amount = (int) atmList.stream()
                    .flatMap(atm -> atm.getCassettes().stream())
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 5)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total10Amount = (int) atmList.stream()
                    .flatMap(atm -> atm.getCassettes().stream())
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 10)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total20Amount = (int) atmList.stream()
                    .flatMap(atm -> atm.getCassettes().stream())
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 20)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total50Amount = (int) atmList.stream()
                    .flatMap(atm -> atm.getCassettes().stream())
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 50)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total100Amount = (int) atmList.stream()
                    .flatMap(atm -> atm.getCassettes().stream())
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 100)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total200Amount = (int) atmList.stream()
                    .flatMap(atm -> atm.getCassettes().stream())
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 200)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            //

            List<WithdrawalCash> withdrawalCashes = (List<WithdrawalCash>) withdrawalCashService.getCashes();

            String dataRowsAmount = generateStats (1,withdrawalCashes);
            model.addAttribute("dataRowsAmount", dataRowsAmount);

            String dataRowsSum = generateStats (2,withdrawalCashes);
            model.addAttribute("dataRowsSum", dataRowsSum);

            //

        } else {

            Atm atm = atmService.getAtm(atmId);

            if (atm.getAtmState().equals("Нормальный")) {
                normalCount = 1;
                problemCount = 0;
                uncertainCount = 0;
            } else if (atm.getAtmState().equals("Проблематичный")) {
                problemCount = 1;
                uncertainCount = 0;
                normalCount = 0;
            } else {
                uncertainCount = 1;
                problemCount = 0;
                normalCount = 0;
            }


            total5Amount = (int) atm.getCassettes().stream()
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 5)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total10Amount = (int) atm.getCassettes().stream()
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 10)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total20Amount = (int) atm.getCassettes().stream()
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 20)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total50Amount = (int) atm.getCassettes().stream()
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 50)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total100Amount = (int) atm.getCassettes().stream()
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 100)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            total200Amount = (int) atm.getCassettes().stream()
                    .filter(cassette -> Integer.parseInt(cassette.getBanknote()) == 200)
                    .mapToDouble(cassette -> cassette.getAmount() * Integer.parseInt(cassette.getBanknote()))
                    .sum();

            //

            List<WithdrawalCash> withdrawalCashes = (List<WithdrawalCash>) withdrawalCashService.getCash(atmId);

            String dataRowsAmount = generateStats (1,withdrawalCashes);
            model.addAttribute("dataRowsAmount", dataRowsAmount);

            String dataRowsSum = generateStats (2,withdrawalCashes);
            model.addAttribute("dataRowsSum", dataRowsSum);

            //
            atmId = null;

        }

        model.addAttribute("total5Amount", total5Amount);
        model.addAttribute("total10Amount", total10Amount);
        model.addAttribute("total20Amount", total20Amount);
        model.addAttribute("total50Amount", total50Amount);
        model.addAttribute("total100Amount", total100Amount);
        model.addAttribute("total200Amount", total200Amount);

        model.addAttribute("normalCount", normalCount);
        model.addAttribute("problemCount", problemCount);
        model.addAttribute("uncertainCount", uncertainCount);

        Sidebar sidebar = new Sidebar();
        sidebar.getDropDown("/analytics", companyService, model);

        return "analytics";
    }

    private String generateStats (int choice, List<WithdrawalCash> withdrawalCashes) {

        Map<LocalDate, Map<Integer, Integer>> amountByDateAndBanknote = null;

        if (choice == 1) {
            amountByDateAndBanknote = withdrawalCashes.stream()
                    .collect(Collectors.groupingBy(WithdrawalCash::getWithdrawalDate,
                            Collectors.groupingBy(cash -> Integer.parseInt(cash.getCassette().getBanknote()),
                                    Collectors.summingInt(WithdrawalCash::getAmount))));
        } else {
            amountByDateAndBanknote = withdrawalCashes.stream()
                    .collect(Collectors.groupingBy(WithdrawalCash::getWithdrawalDate,
                            Collectors.groupingBy(cash -> Integer.parseInt(cash.getCassette().getBanknote()),
                                    Collectors.summingInt(cash -> cash.getAmount() * Integer.parseInt(cash.getCassette().getBanknote())))
                    ));
        }


        List<List<Object>> rows = new ArrayList<>();
        amountByDateAndBanknote.forEach((date, banknoteMap) -> {
            List<Object> row = new ArrayList<>();
            row.add(toJavaScriptDate(date));
            banknoteMap.forEach((banknote, amount) -> row.add(amount));

            // Добавление недостающих значений 0
            int numMissingValues = 6 - banknoteMap.size();
            for (int i = 0; i < numMissingValues; i++) {
                row.add(0);
            }

            rows.add(row);
        });

        String dataRows = toJavaScriptArray(rows);
        dataRows = dataRows.replaceAll("'", "");
        return dataRows;
    }

    private Integer atmId = null;

    // Преобразование объекта LocalDate в объект JavaScript Date
    public static String toJavaScriptDate(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue() - 1; // Месяцы в JavaScript начинаются с 0
        int day = date.getDayOfMonth();
        return "new Date(" + year + ", " + month + ", " + day + ")";
    }

    // Преобразование списка списков в строку JavaScript
    public static String toJavaScriptArray(List<List<Object>> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (List<Object> row : data) {
            sb.append("[");
            for (Object value : row) {
                if (value instanceof String) {
                    sb.append("'").append(value).append("'");
                } else {
                    sb.append(value);
                }
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // Удаление последней запятой
            sb.append("],");
        }
        sb.deleteCharAt(sb.length() - 1); // Удаление последней запятой
        sb.append("]");
        return sb.toString();
    }

    @GetMapping("/{id}")
    public String atmAnalytic(Model model, @PathVariable Integer id) {

        atmId = id;

        return "redirect:/analytics";
    }

}
