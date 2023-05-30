package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Atm;
import com.cashmanagement.vitalyevich.server.model.User;
import com.cashmanagement.vitalyevich.server.model.WithdrawalCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalCashRepository extends JpaRepository<WithdrawalCash, Integer> {

    Iterable<WithdrawalCash> findByAtm(Atm atm);
}
