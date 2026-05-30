package com.learning.telusko.jobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;
    //UserDetailsService is an interface so we need to create an implementation class for it.

    @Bean
    public AuthenticationProvider authProvider(){


        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); //when user is accessing the endpoint/app, int input password is also encoded.
        return provider;

    }

    //we can have multiple auth provider in an application


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http.csrf(customizer->customizer.disable());
        http.authorizeHttpRequests(request->request.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


}


/**
 * We now store and transfer data in encrypted format
 *
 * Cryptography : encrypt a message(with a key) and decrypt it later(with a key)
 * Hashing : is one way (MD5, SHA256)
 * xyz - > hash(axyz) : we cannt get xyz from hashed value
 * so we hash the data multiple times(2 times/5 times/10 times...)
 *
 * BCrypt: helps to encode password
 */

/**
 *
 */