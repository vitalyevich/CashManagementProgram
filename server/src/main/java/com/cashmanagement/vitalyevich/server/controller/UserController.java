package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.firebase.model.WorkTime;
import com.cashmanagement.vitalyevich.server.model.*;
import com.cashmanagement.vitalyevich.server.repository.*;
import com.cashmanagement.vitalyevich.server.service.FBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccessRepository accessRepository;
    @Autowired
    private BrigadeRepository brigadeRepository;

    @QueryMapping
    Iterable<User> users () {
        return userRepository.findByOrderByIdAsc();
    }

    @MutationMapping
    User createUser(@Argument User user, @Argument Role role, @Argument Company company) {
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(role);
        user.setRoles(roles);

        Set<Company> companies = new LinkedHashSet<>();
        companies.add(company);
        user.setCompanies(companies);

        return userRepository.save(user);
    }

    @MutationMapping
    User updateUser(@Argument User user, @Argument Role role, @Argument Company company) {
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(role);
        user.setRoles(roles);

        Set<Company> companies = new LinkedHashSet<>();
        companies.add(company);
        user.setCompanies(companies);

        return userRepository.save(user);
    }

    @MutationMapping
    void deleteUser(@Argument Integer id) {
        userRepository.deleteById(id);
    }

    @QueryMapping
    Iterable<Role> roles () {
        return  roleRepository.findAll();
    }

    @QueryMapping
    User user (@Argument Integer id) {
        return userRepository.findUserById(id);
    }

    @QueryMapping
    Optional<Access> access (@Argument Integer id) {
        return accessRepository.findAccessByUserId(id);
    }

    @MutationMapping
    Access updateAccess(@Argument Access access, @Argument User user) {
        access.setUser(user);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(access.getUserPassword());
        access.setUserPassword(password);
        return accessRepository.save(access);
    }

    @MutationMapping
    Access createAccess(@Argument Access access, @Argument User user) {
        access.setUser(user);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(access.getUserPassword());
        access.setUserPassword(password);
        return accessRepository.save(access);
    }

    @QueryMapping
    Iterable<Access> accesses () {
        return accessRepository.findByOrderByIdAsc();
    }

    @QueryMapping
    Iterable<Brigade> brigades () {
        return  brigadeRepository.findAll();
    }

    @MutationMapping
    Brigade createBrigade(@Argument Brigade brigade, @Argument Company company, @Argument Set<User> users) {

        brigade.setCompany(company);

        brigade.setUsers(users);

        return brigadeRepository.save(brigade);
    }

    @Autowired
    FBService fbService;

    @MutationMapping
    void createWork(@Argument WorkTime work) throws ExecutionException, InterruptedException {
        fbService.saveWork(work);
    }

    @MutationMapping
    void deleteBrigade(@Argument Integer id) {
        brigadeRepository.deleteById(id);
    }

    @QueryMapping
    Optional<Access> accessByLogin (@Argument String login) {
        return accessRepository.findAccessByLogin(login);
    }
}
