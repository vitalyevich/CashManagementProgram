package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.User;
import com.cashmanagement.vitalyevich.server.model.WithdrawalCash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalCashRepository extends JpaRepository<WithdrawalCash, Integer> {
}
