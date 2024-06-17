package com.juicycool.backend.domain.email.service;

import com.juicycool.backend.domain.email.presentation.dto.request.VerificationMailRequestDto;

public interface VerificationMailService {
    void execute(VerificationMailRequestDto dto);
}
