package com.juicycool.backend.domain.auth.service;

import com.juicycool.backend.domain.auth.presentation.dto.request.SignInRequestDto;
import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;

public interface SignInService {
    TokenResponse execute(SignInRequestDto dto);
}
