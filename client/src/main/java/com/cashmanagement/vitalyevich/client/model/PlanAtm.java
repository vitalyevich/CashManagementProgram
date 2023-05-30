package com.cashmanagement.vitalyevich.client.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PlanAtm extends ColorTable {

    private Integer id;

    private Atm atm;

    private String planMethod;

    private String status;

    private Integer planPeriod;

    private User user;

    private String listCassettes;

    private String currency;

    private Integer amount;

    private Order order;

    private String parameter;

    private String marked;

    private String value;

    private String banknote;

    private String amountCassette;

    private Integer sum;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getAmountCassette() {
        return amountCassette;
    }

    public void setAmountCassette(String amountCassette) {
        this.amountCassette = amountCassette;
    }

    public String getBanknote() {
        return banknote;
    }

    public void setBanknote(String banknote) {
        this.banknote = banknote;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    private String date;

    public String getDate() {
        if (order == null) {
            return " ";
        }
        return order.getOrderDate().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getListCassettes() {
        return listCassettes;
    }

    public void setListCassettes(String listCassettes) {
        this.listCassettes = listCassettes;
    }

    public String getCurrency() {
        if (!cassettes.iterator().hasNext()) {
            return " ";
        }
        return cassettes.iterator().next().getCurrency();
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String getColorFirst() {

        if (status.equals("Рассчитан")) {
            return "#3AACED";
        } else if (status.equals("Изменен")) {
            return "#F1CB00";
        }
        else if (status.equals("Принят")) {
            return "#57DB4E";
        }
        else {
            return "#FF3F3F";
        }
    }

    private Set<Cassette> cassettes = new LinkedHashSet<>();

    public Set<Cassette> getCassettes() {
        return cassettes;
    }

    public void setCassettes(Set<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    public Integer getPlanPeriod() {
        return planPeriod;
    }

    public void setPlanPeriod(Integer planPeriod) {
        this.planPeriod = planPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlanMethod() {
        return planMethod;
    }

    public void setPlanMethod(String planMethod) {
        this.planMethod = planMethod;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlanAtm() {
    }

    public PlanAtm(Atm atm) {
        this.atm = atm;
    }

    public PlanAtm(String date) {
        this.date = date;
    }

    public PlanAtm(Integer planPeriod) {
        this.planPeriod = planPeriod;
    }

}