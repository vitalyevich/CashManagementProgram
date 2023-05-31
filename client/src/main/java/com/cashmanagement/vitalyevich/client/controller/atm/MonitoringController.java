package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.service.FilterService;
import com.cashmanagement.vitalyevich.client.model.*;
import com.cashmanagement.vitalyevich.client.service.AtmServiceImpl;
import com.cashmanagement.vitalyevich.client.service.CompanyServiceImpl;
import com.cashmanagement.vitalyevich.client.service.WithdrawalCashServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/monitoring")
@Controller
public class MonitoringController {

    private Integer atmId = 1;

    private Seance seance = Seance.getInstance();

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private WithdrawalCashServiceImpl withdrawalCashService;

    @Autowired
    private AtmServiceImpl atmService;

    @Autowired
    private FilterService filterService;

    private List<Atm> atms = new ArrayList<>();

    @GetMapping("")
    public String monitoring(Model model) {


        if (filter == false) {
            atms = (List<Atm>) atmService.getAtms();
            atm = "";
            cash = "";
            currency = "";
        } else {
            atmId = null;
            atms = filteredAtmList;
            filter = false;
        }

        model.addAttribute("selectedOptionCurrency", currency);
        model.addAttribute("selectedOptionCash",cash);
        model.addAttribute("selectedOptionAtm",atm);

        if (atms == null) {
            return "/error/500";
        }

        for (Atm atm : atms) {
            atm.setListCassettes("");
            atm.setAmount(0);
            if (!atm.getCassettes().iterator().hasNext()) {
                atm.setListCassettes(" ");
            }
            for (Cassette cassette: atm.getCassettes())
            {
                atm.setListCassettes(atm.getListCassettes() +
                        cassette.getAmount() + " ("+cassette.getBanknote()+")" + ", ");
                atm.setAmount(atm.getAmount() + cassette.getAmount());
            }
        }

        if (atmId != null) {
            atms.get(atmId-1).setMarked("marked");
            model.addAttribute("id", atmId-1);

            getWithdrawalCash();
            Atm atm = atmService.getAtm(atmId);
            model.addAttribute("text", atm.getAtmUid()+", История транзакционной активности, с 01.11.2023 до 03.11.2023");
            model.addAttribute("disabled", false);
            model.addAttribute("marked", "marked");
            model.addAttribute("cash", withdrawalCash);


            Map<LocalDate, DoubleSummaryStatistics> statisticsByDate = withdrawalCash.stream()
                    .collect(Collectors.groupingBy(WithdrawalCash::getWithdrawalDate,
                            Collectors.summarizingDouble(WithdrawalCash::getAmount)));

            List<List<Object>> rows = new ArrayList<>();
            statisticsByDate.forEach((date, statistics) -> {
                List<Object> row = new ArrayList<>();
                row.add(toJavaScriptDate(date));
                row.add(statistics.getSum());
                row.add(statistics.getAverage());
                rows.add(row);
            });

            String dataRows = toJavaScriptArray(rows);
            dataRows = dataRows.replaceAll("'", "");

            model.addAttribute("dataRowsAmount", dataRows);



        } else {
            model.addAttribute("disabled", true);
        }

        model.addAttribute("atms", atms);
        model.addAttribute("headerText", "Мониторинг");
        model.addAttribute("headerPost", "Старший кассир " + seance.getUser().getFirstName());


        Sidebar sidebar = new Sidebar();
        sidebar.getDropDown("/monitoring", companyService, model);

        filterService.getValues(model, "/monitoring", atmId, atms);

        return "monitoring";
    }

    // Преобразование объекта LocalDate в объект JavaScript Date
    public static String toJavaScriptDate(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue() - 1; // Месяцы в JavaScript начинаются с 0
        int day = date.getDayOfMonth();
        return "new Date(" + year + ", " + month + ", " + day + ")";
    }

    public static String toJavaScriptArray(List<List<Object>> data) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true; // Флаг для отслеживания первого элемента

        for (List<Object> row : data) {
            if (!isFirst) {
                sb.append(",");
            }
            sb.append("[");
            isFirst = false;

            for (Object value : row) {
                if (value instanceof String) {
                    sb.append("'").append(value).append("'");
                } else {
                    sb.append(value);
                }
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // Удаление последней запятой
            sb.append("]");
        }

        return sb.toString();
    }


    private List<WithdrawalCash> withdrawalCash = new LinkedList<>();

    private void getWithdrawalCash() {
        withdrawalCash = new LinkedList<>();

        withdrawalCash = (List<WithdrawalCash>) withdrawalCashService.getCash(atmId);

        for (WithdrawalCash withdrawalCash1: withdrawalCash) {

            int sum = withdrawalCash1.getAmount() * Integer.parseInt(withdrawalCash1.getCassette().getBanknote());
            withdrawalCash1.getCassette().setSumAmount(sum);
        }
    }

    @GetMapping("/{id}")
    public String atmMonitoring(@PathVariable Integer id) {

        atmId = id;

        return "redirect:/monitoring";
    }


    private Boolean filter = false;

    List<Atm> filteredAtmList = new ArrayList<>();

    private String currency = "";
    private String cash = "";
    private String atm = "";


    @GetMapping("/stats")
    public String stats(Model model) {

        return "redirect:/monitoring";
    }

    @PostMapping("/filter/currency")
    public String currencyFilter(@RequestParam("currency") String currency) {
        if (currency == "") {
            filter = false;
        } else {
            atms = (List<Atm>) atmService.getAtms();
            this.currency = currency;
            filteredAtmList = atms.stream()
                    .filter(atm -> atm.getCurrency().equals(currency))
                    .collect(Collectors.toList());

            filter = true;
        }
        return "redirect:/monitoring";
    }

    @PostMapping("/filter/cash")
    public String cashFilter(@RequestParam("cash") String cash) {
        if (cash == "") {
            filter = false;
        } else {
            atms = (List<Atm>) atmService.getAtms();
            this.cash = cash;
            filteredAtmList = atms.stream()
                    .filter(atm -> atm.getCashState().equals(cash))
                    .collect(Collectors.toList());

            filter = true;
        }
        return "redirect:/monitoring";
    }

    @PostMapping("/filter/atm")
    public String atmFilter(@RequestParam("atm") String atm) {
        if (atm == "") {
            filter = false;
        } else {
            atms = (List<Atm>) atmService.getAtms();
            this.atm = atm;
            filteredAtmList = atms.stream()
                    .filter(a -> a.getAtmState().equals(atm))
                    .collect(Collectors.toList());
            filter = true;
        }
        return "redirect:/monitoring";
    }
}
