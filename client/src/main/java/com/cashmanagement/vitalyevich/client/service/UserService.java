package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.firebase.model.WorkTime;
import com.cashmanagement.vitalyevich.client.model.Access;
import com.cashmanagement.vitalyevich.client.model.Company;
import com.cashmanagement.vitalyevich.client.model.Role;
import com.cashmanagement.vitalyevich.client.model.User;

public interface UserService {

    Iterable<User> getUsers();

    User getUser(Integer id);

    User saveUser(User user, Integer roleId);

    User updateUser(User user, Integer roleId);

    void deleteUser(Integer id);

    Iterable<Role> getRoles();

    Iterable<Company> getCompany();

    void saveWork(WorkTime workTime);

    Iterable<Access> getAccesses();

    Access getAccess(Integer id);

    Access updateAccess(Access access, Integer userId);

    Access saveAccess(Access access, Integer userId);
}
