package com.juicycool.backend.domain.auth.service;

import com.juicycool.backend.domain.auth.presentation.dto.request.SignUpRequestDto;

public interface SignUpService {
    void execute(SignUpRequestDto dto);
}
