package com.fitness.apigateway;

import com.fitness.apigateway.user.RegisterRequest;
import com.fitness.apigateway.user.UserService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeyclockUserSyncFilter implements WebFilter {

    private final UserService userService;

    @Override
    public Mono<Void> filter (ServerWebExchange exchange, WebFilterChain chain){
        String userId = exchange.getRequest().getHeaders().getFirst("X-User-ID");
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        System.out.println("Token is : " + token);
        RegisterRequest registerRequest = getUserDetails(token);


        if(userId == null ){
            userId = registerRequest.getKeycloakId();
        }

        if(userId != null && token != null){
            String finalUserId = userId;
            return userService.validateUser(registerRequest.getKeycloakId())
                    .flatMap(exists ->{
                        if(!exists){
                            //Register user

                            if(registerRequest != null){
                                return userService.registerUser(registerRequest)
                                        .then(Mono.empty());
                            }
                            else{
                                return Mono.empty();
                            }

                        }
                        else{
                            log.info("User already exists, skipping sync");
                            return Mono.empty();
                        }
                    })
                    .then(Mono.defer(() -> {
                            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                            .header("X-User-ID", finalUserId)
                                    .build();
                            return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    }));
        }
        return chain.filter(exchange);


    }

    private RegisterRequest getUserDetails(String token) {

        try{
            String tokenWithoutBearer = token.replace("Bearer ","").trim();
            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            RegisterRequest registerRequest =  new RegisterRequest();

            registerRequest.setEmail(claims.getStringClaim("email"));
            registerRequest.setKeycloakId(claims.getStringClaim("sub"));
            registerRequest.setPassword("dummy@123123");
            registerRequest.setFirstName(claims.getStringClaim("given_name"));
            registerRequest.setLastName(claims.getStringClaim("family_name"));

            return registerRequest;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

/***
 *
 * Explaing this statement:
 *  public Mono<Void> filter (ServerWebExchange exchange, WebFilterChain chain){
 *  
 * https://www.perplexity.ai/search/afe60015-9523-4652-9222-91af57aef8f8
 *
 * A filter is a component that intercepts an HTTP request before it reaches the controller
 * and can also process the response afterward.
 *
 * In Spring WebFlux, a WebFilter is commonly used for cross-cutting tasks such as logging,
 * authentication, authorization, adding headers, tracing, and error handling.
 * A WebFilter can apply to all endpoints in the application.
 */