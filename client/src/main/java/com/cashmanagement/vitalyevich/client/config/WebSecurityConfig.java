package com.cashmanagement.vitalyevich.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

/* @Autowired
    private DataSource dataSource;*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
                .authorizeRequests()
                .mvcMatchers("/profile", "/menu/rolls#blackout-basket", "/order", "/basket").hasAnyRole("USER", "ADMIN")
                .mvcMatchers("/admin/**").hasRole("ADMIN")

                .antMatchers("/users").permitAll().and().httpBasic();
                .anyRequest().authenticated()
                .and()
                .formLogin()
 .loginPage("/authorization")
                .failureUrl("/authorization?error=true")

                .defaultSuccessUrl("/profile",true)
.usernameParameter("phone")
                .passwordParameter("password")

                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/menu/rolls");*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**",
                        "/blocks/**", "/users/**", "/profiles/**");
    }

 @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // .passwordEncoder(passwordEncoder);

/*  auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select phone, password, active from access where phone=?")
                .authoritiesByUsernameQuery("select A.phone, r.role_name from access A \n" +
                        "inner join user_roles ur on A.id = ur.user_id \n" +
                        "inner join roles r on ur.role_id = r.id where a.phone=?");*/
    }

}
