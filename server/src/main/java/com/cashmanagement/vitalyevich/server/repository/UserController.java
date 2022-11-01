package com.cashmanagement.vitalyevich.server.repository;

import com.cashmanagement.vitalyevich.server.model.Access;
import com.cashmanagement.vitalyevich.server.model.User;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;

    private final AccessRepository accessRepository;

    public UserController(UserRepository userRepository, AccessRepository accessRepository) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
    }

    @QueryMapping
    Iterable<User> users () {
        return  userRepository.findAll();
    }

    @QueryMapping
    Optional<User> user () {
        return userRepository.findById(1);
    }

    @QueryMapping
    Iterable<Access> accesses () {
        return  accessRepository.findAll();
    }
}
