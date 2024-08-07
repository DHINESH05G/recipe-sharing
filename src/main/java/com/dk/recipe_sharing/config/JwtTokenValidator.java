package com.dk.recipe_sharing.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader(JwtConstant.JWT_HEADER);
        if(jwt!=null)
        {
            jwt=jwt.substring(7);
            try
            {
                SecretKey key= Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
                Claims claims= Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
                String email=claims.get("email").toString();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,null,null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception ex)
            {
                throw new BadRequestException("Invalid Token.....");
            }
        }
        filterChain.doFilter(request,response);
    }
}
