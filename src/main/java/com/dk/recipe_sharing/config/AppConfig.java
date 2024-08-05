package com.dk.recipe_sharing.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppConfig {
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**")
                        .authenticated().anyRequest().permitAll())
                .csrf(csrf-> csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigrationSource()))
                //.httpBasic(withDefaults())
                .formLogin(withDefaults());
        return http.build();
    }

    private CorsConfigurationSource corsConfigrationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.addAllowedOrigin("*");
                cfg.addAllowedMethod("*");
                cfg.addAllowedHeader("*");
                cfg.addExposedHeader("*");
                cfg.setMaxAge(3600L);
                return cfg;
            }
        };
    }
}
