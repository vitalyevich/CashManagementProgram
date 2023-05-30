package com.cashmanagement.vitalyevich.client.controller.users;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.Access;
import com.cashmanagement.vitalyevich.client.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class PageAccessFilter extends GenericFilterBean {

    private RequestMatcher requestMatcher;

    private Seance seance = Seance.getInstance();

    private boolean filterExecuted = false;

    @Autowired
    private UserServiceImpl userService;

    public PageAccessFilter() {
        this.requestMatcher = new AntPathRequestMatcher("/**");
        this.userService = new UserServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Проверяем, соответствует ли текущий запрос указанному пути
        if (requestMatcher.matches(httpRequest) && !filterExecuted) {

            filterExecuted = true;

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                String login = authentication.getName();
                if (!login.equals("anonymousUser")) {

                    Access access = userService.authorization(login);

                    seance.setUser(access.getUser());

                }
            }
        }

        chain.doFilter(request, response);
    }
}
