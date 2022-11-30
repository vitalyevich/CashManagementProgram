package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Brigade;
import com.cashmanagement.vitalyevich.server.model.Cassette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrigadeRepository extends JpaRepository<Brigade, Integer> {
}
