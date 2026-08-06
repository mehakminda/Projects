package com.email.emailwritersb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}

/**
 *
 *
 * Added this class because was getting this error
 *
 * ***************************
 * APPLICATION FAILED TO START
 * ***************************
 *
 * Description:
 *
 * Parameter 0 of constructor in com.email.emailwritersb.service.EmailGeneratorService required a bean of type 'org.springframework.web.reactive.function.client.WebClient$Builder' that could not be found.
 *
 *
 * Action:
 *
 * Consider defining a bean of type 'org.springframework.web.reactive.function.client.WebClient$Builder' in your configuration.
 *
 */