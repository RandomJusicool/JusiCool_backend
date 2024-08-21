package com.juicycool.backend.global.security.jwt;

import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.juicycool.backend.global.auth.AuthDetailsService;
import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;
import com.juicycool.backend.global.security.exception.ExpiredTokenException;
import com.juicycool.backend.global.security.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_TIME = 60L * 15 * 4;
    public static final long REFRESH_TOKEN_TIME = 60L * 60 * 24 * 7;

    private final AuthDetailsService authDetailsService;
    private final JwtProperties jwtProperties;
    private final TokenParser tokenParser;

    private Key getSignInAccessKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getAccessSecret().getBytes(StandardCharsets.UTF_8));
    }

    private Key getSignInSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getRefreshSecret().getBytes(StandardCharsets.UTF_8));
    }

    public TokenResponse generateTokenDto(String email) {
        return TokenResponse.builder()
                .accessToken(generateAccessToken(email))
                .refreshToken(generateRefreshToken(email))
                .accessTokenExpiresIn(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME))
                .refreshTokenExpiresIn(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME))
                .build();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInAccessKey()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = tokenParser.parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new GlobalException(ErrorCode.INVALID_TOKEN);
        }
        UserDetails principal = authDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    public String generateAccessToken(String email) {
        Date accessTokenExpiresIn = new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME*1000);

        return Jwts.builder()
                .setSubject(email)
                .claim(AUTHORITIES_KEY, "JWT")
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiresIn)
                .signWith(getSignInAccessKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String email) {
        Date refreshTokenExpiresIn = new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME*1000);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(getSignInSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}