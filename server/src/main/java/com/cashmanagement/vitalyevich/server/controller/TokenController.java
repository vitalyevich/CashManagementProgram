package com.cashmanagement.vitalyevich.server.controller;

import com.cashmanagement.vitalyevich.server.config.JwtGenerator;
import com.cashmanagement.vitalyevich.server.config.JwtTokenProvider;
import com.cashmanagement.vitalyevich.server.model.Access;
import com.cashmanagement.vitalyevich.server.model.JwtToken;
import com.cashmanagement.vitalyevich.server.model.JwtUser;
import com.cashmanagement.vitalyevich.server.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TokenController {

    private JwtGenerator jwtGenerator;

    @Autowired
    private UserController userController;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/authorization")
    public String authorization(@RequestBody final JwtUser jwtUser) {


        Optional<Access> access =  userController.accessByLogin(jwtUser.getUserName());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (access != null && passwordEncoder.matches(jwtUser.getPassword(), access.get().getUserPassword())) {
            return jwtGenerator.generate(jwtUser);
        }

        return null;

    }

    @PostMapping("/login")
    public String login(@RequestBody final JwtUser jwtUser) {

        Optional<Access> access =  userController.accessByLogin(jwtUser.getUserName());

        return jwtGenerator.generate(jwtUser);

    }

/*
    @MutationMapping
    String login(@Argument String userName, @Argument String password) {
        String name = "";
        return "УСПЕХ";
    }*/
}
