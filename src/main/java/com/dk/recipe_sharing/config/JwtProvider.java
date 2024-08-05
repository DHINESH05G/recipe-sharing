package com.dk.recipe_sharing.config;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import io.jsonwebtoken.*;

@Service
public class JwtProvider {
    public String generateToken(Authentication auth)
    {
        String jwt=Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getAuthorities())
                .signWith(null)
                .compact();
        return jwt;
    }

}
