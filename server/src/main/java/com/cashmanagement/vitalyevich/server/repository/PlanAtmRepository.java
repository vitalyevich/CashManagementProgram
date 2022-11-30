package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Company;
import com.cashmanagement.vitalyevich.server.model.PlanAtm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanAtmRepository extends JpaRepository<PlanAtm, Integer> {
}
