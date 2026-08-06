package com.fitness.apigateway.user;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserResponse {

    private String id;
    private String keycloakId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    //private char[] profilePhoto;
}
