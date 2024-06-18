package com.juicycool.backend.domain.auth.service.impl;

import com.juicycool.backend.domain.auth.RefreshToken;
import com.juicycool.backend.domain.auth.exception.NotMatchPasswordException;
import com.juicycool.backend.domain.auth.presentation.dto.request.SignInRequestDto;
import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.juicycool.backend.domain.auth.repository.RefreshTokenRepository;
import com.juicycool.backend.domain.auth.service.SignInService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.exception.NotFoundUserException;
import com.juicycool.backend.domain.user.repository.UserRepository;
import com.juicycool.backend.global.annotation.TransactionService;
import com.juicycool.backend.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@TransactionService
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public TokenResponse execute(SignInRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(NotFoundUserException::new);

        if (passwordEncoder.matches(user.getPassword(), dto.getPassword()))
            throw new NotMatchPasswordException();

        TokenResponse tokenResponse = jwtProvider.generateTokenDto(user.getId());

        saveRefreshToken(user, tokenResponse.getRefreshToken());

        return tokenResponse;
    }

    private void saveRefreshToken(User user, String token) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(user.getId())
                .token(token)
                .build();

        refreshTokenRepository.save(refreshToken);
    }
}
