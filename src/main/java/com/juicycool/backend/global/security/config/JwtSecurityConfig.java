package com.juicycool.backend.global.security.config;

import com.juicycool.backend.global.filter.JwtFilter;
import com.juicycool.backend.global.security.jwt.JwtProvider;
import com.juicycool.backend.global.security.jwt.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtProvider jwtProvider;
    private final TokenParser tokenParser;

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(jwtProvider, tokenParser);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

