package com.cashmanagement.vitalyevich.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        /*registry.addViewController("/menu/rolls#blackout-authorization").setViewName("blocks/authorization");*/
        registry.addViewController("/users").setViewName("users");
    }
}
