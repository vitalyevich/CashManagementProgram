package com.cashmanagement.vitalyevich.client.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;

@Configuration
public class GraphClient {

    @Bean()
    public HttpGraphQlClient httpGraphQlClient() {
        //WebClient webClient = WebClient.create("http://localhost:9191/graphql");
        //HttpGraphQlClient graphQlClient = HttpGraphQlClient.create(webClient);

        return HttpGraphQlClient.builder().url("http://localhost:9191/graphql").build();
    }
}
