package com.juicycool.backend.domain.auth.service.impl;

import com.juicycool.backend.domain.auth.RefreshToken;
import com.juicycool.backend.domain.auth.exception.ExpiredRefreshTokenException;
import com.juicycool.backend.domain.auth.exception.InvalidRefreshTokenException;
import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.juicycool.backend.domain.auth.repository.RefreshTokenRepository;
import com.juicycool.backend.domain.auth.service.ReissueTokenService;
import com.juicycool.backend.global.annotation.TransactionService;
import com.juicycool.backend.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@TransactionService
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public TokenResponse execute(String refreshToken) {
        String parseToken = jwtProvider.parseRefreshToken(refreshToken);

        RefreshToken existedRefreshToken = refreshTokenRepository.findByToken(parseToken)
                .orElseThrow(ExpiredRefreshTokenException::new);

        if (!Objects.equals(existedRefreshToken.getToken(), parseToken))
            throw new InvalidRefreshTokenException();

        TokenResponse tokenResponse = jwtProvider.generateTokenDto(existedRefreshToken.getEmail());

        saveRefreshToken(tokenResponse.getRefreshToken(), existedRefreshToken.getEmail());

        return tokenResponse;
    }

    private void saveRefreshToken(String refreshToken, String email) {
        refreshTokenRepository.deleteById(email);

        RefreshToken token = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        refreshTokenRepository.save(token);
    }
}
