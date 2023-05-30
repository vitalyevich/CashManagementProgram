package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.User;
import com.cashmanagement.vitalyevich.client.model.WithdrawalCash;

public interface WithdrawalCashService {


    Iterable<WithdrawalCash> getCashes();
    Iterable<WithdrawalCash> getCash(Integer id);
}
