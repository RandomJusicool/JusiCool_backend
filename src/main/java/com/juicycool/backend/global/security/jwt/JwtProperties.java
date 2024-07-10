package com.juicycool.backend.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("jwt")
public class JwtProperties {
    private final String accessSecret;
    private final String refreshSecret;
}
