package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.User;

public interface AtmService {

    Iterable<Atm> getAtms();

}
