package com.cashmanagement.vitalyevich.server.config;

import com.cashmanagement.vitalyevich.server.model.Access;
import com.cashmanagement.vitalyevich.server.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "ljnifphPm0bsRAfIeO1wMHbk2DUuNGuobbrTcSqe5+4UQA1I1tMm6KSUVgSE1LEV6tPyVoPqYaj8r2/KtgRRuQ==";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setPassword((String) body.get("password")); // password
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
