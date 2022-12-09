package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.firebase.model.WorkTime;
import com.cashmanagement.vitalyevich.client.model.*;

public interface UserService {

    Iterable<User> getUsers();

    User getUser(Integer id);

    User saveUser(User user, Integer roleId, Integer companyId);

    User updateUser(User user, Integer roleId, Integer companyId);

    void deleteUser(Integer id);

    Iterable<Role> getRoles();

    void saveWork(WorkTime workTime);

    Iterable<Access> getAccesses();

    Access getAccess(Integer id);

    Access updateAccess(Access access, Integer userId);

    Access saveAccess(Access access, Integer userId);

    Iterable<Brigade> getBrigades();
}
