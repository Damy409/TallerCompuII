package org.demo1.service.auth;

public class AuthResponse {
    private String token;

    public AuthResponse() {} // <-- important for Jackson

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
