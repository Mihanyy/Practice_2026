package com.example.bb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {
            http.csrf(csrfConf ->
                    csrfConf.disable()
            );

            http.sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
            http.authorizeHttpRequests(authorization -> {

                authorization.requestMatchers(HttpMethod.POST, "/users/register").permitAll();
                authorization.anyRequest().authenticated();

            });

            http.httpBasic(Customizer.withDefaults());

            SecurityFilterChain filterChain = http.build();

            return filterChain;

        } catch (Exception e){
            throw new IllegalStateException("Ошибка Spring Security", e);
        }
    }
}
