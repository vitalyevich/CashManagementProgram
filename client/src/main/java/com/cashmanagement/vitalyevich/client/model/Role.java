package com.cashmanagement.vitalyevich.client.model;

public class Role {

    private Integer id;

    private String roleName;

    private String nameRole;

    public String getRoleName() {
        return roleName;
    }

    public String getNameRole() {
        if (roleName.equals("ADMIN")) {
            return "Руководитель";
        }
        else if (roleName.equals("DEALER")) {
            return "Старший дилер";
        }
        else if (roleName.equals("CASHIER")) {
            return "Старший кассир";
        }
        else if (roleName.equals("CASHIERSTORAGE")) {
            return "Старший кассир хранилища";
        }
        else {
            return "Старший инкассатор";
        }
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role() {
    }

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
}