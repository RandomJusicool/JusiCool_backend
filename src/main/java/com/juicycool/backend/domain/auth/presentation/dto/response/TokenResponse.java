package com.juicycool.backend.domain.auth.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessTokenExpiresIn;
    private LocalDateTime refreshTokenExpiresIn;
}
