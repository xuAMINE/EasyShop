package com.uup.dto;

import com.uup.model.User;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class AuthResponse {

    private String token;
    private Map<String, Object> user;

    public AuthResponse(String token, User userEntity) {
        this.token = token;
        this.user = Map.of(
                "id", userEntity.getUserId(),
                "username", userEntity.getUsername(),
                "authorities", List.of(Map.of("name", userEntity.getRole()))
        );
    }

}

