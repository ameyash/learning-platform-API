package com.gamified.learning_platform.Models;

public class AuthResponse {
    private String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter
    public String getJwt() {
        return jwt;
    }

    // Setter (optional, in case needed)
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

