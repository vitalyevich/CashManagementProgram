package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.BrigadeOrder;
import com.cashmanagement.vitalyevich.server.model.Cassette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrigadeOrderRepository extends JpaRepository<BrigadeOrder, Integer> {
}
