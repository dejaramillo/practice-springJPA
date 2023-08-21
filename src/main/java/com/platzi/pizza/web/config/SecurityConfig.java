package com.platzi.pizza.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class SecurityConfig {

    private static  final String ADMIN_ROLE = "ADMIN";

    private static final String PIZZAS_PATH = "/api/pizzas/**";
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf()
                    .disable()
                    .cors()
                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.GET, PIZZAS_PATH)
                    .hasAnyRole(ADMIN_ROLE, "CUSTOMER")
                    .requestMatchers(HttpMethod.POST, PIZZAS_PATH)
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers(HttpMethod.PUT, PIZZAS_PATH)
                    .hasRole(ADMIN_ROLE)
                    .requestMatchers("/api/orders/random")
                    .hasAuthority("random_order")
                    .requestMatchers("/api/orders/**").hasAnyRole(ADMIN_ROLE)
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();
            return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
