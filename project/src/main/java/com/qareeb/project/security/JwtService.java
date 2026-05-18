package com.qareeb.project.security;

import com.qareeb.project.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "your-secret-key-your-secret-key-your-secret-key";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // 1. Generate token
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getPhoneNumber())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. Extract phone number
    public String extractPhoneNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 3. Extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimsResolver.apply(claims);
    }

    // 4. Validate token
    public boolean isTokenValid(String token, User user) {
        String phone = extractPhoneNumber(token);
        return phone.equals(user.getPhoneNumber()) && !isTokenExpired(token);
    }

    // 5. Check expiration
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}