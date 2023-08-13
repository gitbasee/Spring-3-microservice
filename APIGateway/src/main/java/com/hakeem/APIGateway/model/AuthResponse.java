package com.hakeem.APIGateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String userID;
    private String accessToken;
    private String refreshToken;
    private Long exprireAt;
    private Collection<String> authorise;
}
