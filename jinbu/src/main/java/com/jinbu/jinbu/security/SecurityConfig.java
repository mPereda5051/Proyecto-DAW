package com.jinbu.jinbu.security;

import com.jinbu.jinbu.security.filter.AuthenticationFilter;
import com.jinbu.jinbu.security.filter.ExceptionHandlerFilter;
import com.jinbu.jinbu.security.filter.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.jinbu.jinbu.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
//        .csrf(csrf -> csrf.disable())
//        .authorizeHttpRequests(authorize -> authorize
//            .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
//            .anyRequest().authenticated())
//        .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
//        .addFilter(authenticationFilter)
//        .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
//        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
