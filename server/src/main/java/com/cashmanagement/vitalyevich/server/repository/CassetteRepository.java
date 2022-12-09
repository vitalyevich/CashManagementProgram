package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Cassette;
import com.cashmanagement.vitalyevich.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CassetteRepository extends JpaRepository<Cassette, Integer> {
    Iterable<Cassette> findByOrderByCassetteNumAsc();
}
