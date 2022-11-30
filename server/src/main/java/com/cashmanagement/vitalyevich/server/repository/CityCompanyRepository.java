package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Cassette;
import com.cashmanagement.vitalyevich.server.model.CityCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityCompanyRepository extends JpaRepository<CityCompany, Integer> {
}
