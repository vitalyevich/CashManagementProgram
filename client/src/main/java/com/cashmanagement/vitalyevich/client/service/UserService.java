package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.Company;
import com.cashmanagement.vitalyevich.client.model.Role;
import com.cashmanagement.vitalyevich.client.model.User;

public interface UserService {

    Iterable<User> getUsers();

    Iterable<Role> getRoles();

    Iterable<Company> getCompany();
}
