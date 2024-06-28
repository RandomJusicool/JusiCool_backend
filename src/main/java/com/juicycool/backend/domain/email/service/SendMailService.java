package com.juicycool.backend.domain.email.service;

import com.juicycool.backend.domain.email.presentation.dto.request.SendMailRequestDto;

public interface SendMailService {
    void execute(SendMailRequestDto dto);
}
