package org.demo1.security;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.demo1.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService implements IJwtService{

    @Value("${app.security.jwt.secret-key}")
    private String secretKey;
    @Value("${app.security.jwt.expiration-time}")
    private long expirationTime;

    @Override
    public String generateAccessToken(User user, Authentication auth) {
        return buildToken(user, expirationTime, auth);
    }
    
    private String buildToken(User user, long expirationTime, Authentication auth) {
        Map<String, Object> claims = Map.of(
            "jti", user.getId().toString(),
            "username", user.getName(),
            "email", user.getEmail(),
            "lastName", user.getLastname(),
            "userId", user.getId(),
            "authorities", auth != null && auth.getAuthorities() != null
                ? auth.getAuthorities().stream().map(ga -> ga.getAuthority()).toList()
                : List.of()
        );
    
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(user.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey())  // CORREGIDO: era getSignInKey (sin paréntesis)
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    @Override
    public boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public boolean isTokenValid(String token, User user) {
        String username = extractUsername(token);
        return (username.equals(user.getName()) && !isTokenExpired(token));
    }

    
    
}
