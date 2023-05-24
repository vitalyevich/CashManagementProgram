package com.cashmanagement.vitalyevich.server.config;

import com.cashmanagement.vitalyevich.server.model.Access;
import com.cashmanagement.vitalyevich.server.model.JwtUser;
import com.cashmanagement.vitalyevich.server.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtGenerator {
    public String generate (JwtUser jwtUser) {
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("password", String.valueOf(jwtUser.getPassword()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "ljnifphPm0bsRAfIeO1wMHbk2DUuNGuobbrTcSqe5+4UQA1I1tMm6KSUVgSE1LEV6tPyVoPqYaj8r2/KtgRRuQ==")
                .compact();
    }
}
