package com.cashmanagement.vitalyevich.client.graphql;

import com.cashmanagement.vitalyevich.client.config.Seance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.graphql.client.HttpGraphQlClient;

import javax.annotation.PostConstruct;

@Configuration
public class GraphClient {

    private Seance seance = Seance.getInstance();

    @Bean()
    @Lazy
    public HttpGraphQlClient httpGraphQlClient() {

            return HttpGraphQlClient.builder().url("http://localhost:9191/graphql").header("Authorization", "Token " + seance.getToken()).build();

    }

}
