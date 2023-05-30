package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.PlanAtm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanAtmRepository extends JpaRepository<PlanAtm, Integer> {

    Iterable<PlanAtm> findByOrderByIdAscCassettesAsc();

    Iterable<PlanAtm> findByOrderByIdAsc();

    PlanAtm findPlanAtmByAtmId(Integer integer);
}
