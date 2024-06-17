package com.juicycool.backend.domain.email.service.impl;

import com.juicycool.backend.domain.email.MailAuthEntity;
import com.juicycool.backend.domain.email.exception.ExpiredAuthCodeException;
import com.juicycool.backend.domain.email.presentation.dto.request.VerificationMailRequestDto;
import com.juicycool.backend.domain.email.repository.MailAuthRepository;
import com.juicycool.backend.domain.email.service.VerificationMailService;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@TransactionService
@RequiredArgsConstructor
public class VerificationMailServiceImpl implements VerificationMailService {

    private final MailAuthRepository mailAuthRepository;

    public void execute(VerificationMailRequestDto dto) {
        MailAuthEntity emailAuth = mailAuthRepository.findById(dto.getEmail())
                .orElseThrow(ExpiredAuthCodeException::new);

        if (!Objects.equals(emailAuth.getRandomValue(), dto.getAuthCode()))
            throw new ExpiredAuthCodeException();

        emailAuth.updateAuthentication(true);

        mailAuthRepository.save(emailAuth);
    }
}
