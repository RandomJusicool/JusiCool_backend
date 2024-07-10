package com.juicycool.backend.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import static com.juicycool.backend.global.filter.JwtFilter.AUTHORIZATION_HEADER;
import static com.juicycool.backend.global.filter.JwtFilter.BEARER_PREFIX;


@Component
@RequiredArgsConstructor
public class TokenParser {

    private static final String BEARER_TYPE = "Bearer ";
    private final JwtProperties jwtProperties;

    private Key getSignInAccessKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getAccessSecret().getBytes(StandardCharsets.UTF_8));
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSignInAccessKey()).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String parseRefreshToken(String refreshToken) {
        if (refreshToken.startsWith(BEARER_TYPE)) {
            return refreshToken.replace(BEARER_TYPE, "");
        } else {
            return null;
        }
    }

}
