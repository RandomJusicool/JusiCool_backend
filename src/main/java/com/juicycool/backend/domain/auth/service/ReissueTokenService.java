package com.juicycool.backend.domain.auth.service;

import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;

public interface ReissueTokenService {
    TokenResponse execute(String refreshToken);
}
