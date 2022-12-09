package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.User;

import java.util.Optional;

public interface AtmService {

    Iterable<Atm> getAtms();

    Atm getAtm(Integer id);

}
