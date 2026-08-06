package com.fitness.activityservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced //allows webclient to resolve the service name via eureka
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    @Bean //we have a bean called userServiceWebClient which is configured to point to userService
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder
                .baseUrl("http://USER-SERVICE")
                .build();
    }
   // This only works if USER-SERVICE is resolvable as a real host, or if the builder is
    // specially load-balanced. Otherwise, the client tries DNS resolution on USER-SERVICE,
    // which fails.
}


/**
 * Configuration class for creating webclient Builder
 *
 * specialized client for user service
 *
 * using this we wil make calls to user-service
 */

/**
 *  @LoadBalanced
 *  instead of using the ip address to call the other service we will use service name and
 *  eureka will take care of  ip address
 *
 *  For service-name resolution, the WebClient must be load-balanced with Spring Cloud LoadBalancer.
 *
 *   the service-name resolution comes from Spring Cloud, not plain WebClient.
 *
 */