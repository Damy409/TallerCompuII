package org.demo1.security;

import org.demo1.model.User;
import org.springframework.security.core.Authentication;

public interface IJwtService {
    String generateAccessToken(User user, Authentication auth);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
    boolean isTokenValid(String token, User user);
    
}
