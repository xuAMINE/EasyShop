package com.uup.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    // Getters and setters
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}

