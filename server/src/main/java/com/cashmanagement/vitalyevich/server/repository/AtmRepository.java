package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Atm;
import com.cashmanagement.vitalyevich.server.model.Cassette;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtmRepository extends JpaRepository<Atm, Integer> {

    Optional<Atm> findAllById(Integer id);

    Iterable<Atm> findByOrderById();
}
