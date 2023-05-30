package com.cashmanagement.vitalyevich.client.config;

import com.cashmanagement.vitalyevich.client.controller.users.PageAccessFilter;
import com.cashmanagement.vitalyevich.client.model.Access;
import com.cashmanagement.vitalyevich.client.service.UserService;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().disable().csrf().disable();
        http
                .authorizeRequests()
                .mvcMatchers("/monitoring/**", "/planning/**").hasAnyRole("ADMIN", "CASHIER")
                .mvcMatchers("/monitoring-storage/**", "/storage-collection/**, /monitoring-storages/**").hasAnyRole("ADMIN", "CASHIERSTORAGE")
                .mvcMatchers("/collection/**", "/planning-collection/**").hasAnyRole("ADMIN", "COLLECTOR")
                .mvcMatchers("/analytics/**").hasAnyRole("ADMIN", "DEALER")
                .mvcMatchers("/edit-password", "/users/**").hasRole("ADMIN")
                .mvcMatchers("/profile-dealer").hasRole("DEALER")
                .mvcMatchers("/profile-collection").hasRole("COLLECTOR")
                .mvcMatchers("/profile-cashier").hasRole("CASHIER")
                .mvcMatchers("/profile-cashier-storage/**").hasRole("CASHIERSTORAGE")
                .mvcMatchers("/profile-admin/**").hasRole("ADMIN")
                .antMatchers("/authorization").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/authorization")
                .failureUrl("/authorization?error=true")
                .defaultSuccessUrl("/profile", true)
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/authorization");

        http.addFilterBefore(new PageAccessFilter(), BasicAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**",
                        "/blocks/**", "/users/**", "/profiles/**", "/confirmations/**", "/planning/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);;
    }

    public UserDetailsService userDetailsService() {
        return (username) -> {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String password = request.getParameter("password");

            Access access = userService.authorization(username, password);

            if (access != null) {
                return User.withUsername(username)
                        .password(access.getUserPassword())
                        .roles(access.getUser().getRoles().iterator().next().getRoleName())
                        .accountLocked(access.getActive())
                        .build();

            } else {
                return null;
            }

        };
    }
}
