package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.model.*;
import com.cashmanagement.vitalyevich.server.repository.*;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AccessRepository accessRepository;

    private final CompanyRepository companyRepository;

    private final BrigadeRepository brigadeRepository;

    public UserController(UserRepository userRepository, AccessRepository accessRepository, RoleRepository roleRepository, CompanyRepository companyRepository, BrigadeRepository brigadeRepository) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
        this.brigadeRepository = brigadeRepository;
    }

    @QueryMapping
    Iterable<User> users () {
        return  userRepository.findAll();
    }

    @QueryMapping
    Iterable<Role> roles () {
        return  roleRepository.findAll();
    }

    @QueryMapping
    Optional<User> user (Integer id) {
        return userRepository.findUserById(id);
    }

    @QueryMapping
    Iterable<Access> accesses () {
        return  accessRepository.findAll();
    }

    @QueryMapping
    Iterable<Company> companies () {
        return  companyRepository.findAll();
    }

    @QueryMapping
    Iterable<Brigade> brigades () {
        return  brigadeRepository.findAll();
    }
}
