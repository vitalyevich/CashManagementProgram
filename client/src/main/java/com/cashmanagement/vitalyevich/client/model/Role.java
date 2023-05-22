package com.cashmanagement.vitalyevich.client.model;


import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    private String nameRole;

    public String getRoleName() {
        return roleName;
    }

    public String getNameRole() {
        if (roleName.equals("ROLE_ADMIN")) {
            return "Руководитель";
        }
        else if (roleName.equals("ROLE_DEALER")) {
            return "Старший дилер";
        }
        else if (roleName.equals("ROLE_CASHIER")) {
            return "Старший кассир";
        }
        else if (roleName.equals("ROLE_CASHIERSTORAGE")) {
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