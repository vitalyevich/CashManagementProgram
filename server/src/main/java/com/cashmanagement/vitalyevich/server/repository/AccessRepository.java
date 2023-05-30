package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Access;
import com.cashmanagement.vitalyevich.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<Access, Integer> {
    Optional<Access> findAccessByUserId(Integer id);
    Iterable<Access> findByOrderByIdAsc();

    Optional<Access> findAccessByLogin(String login);
}
