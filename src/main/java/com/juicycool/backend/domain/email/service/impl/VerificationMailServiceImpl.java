package com.juicycool.backend.domain.email.service.impl;

import com.juicycool.backend.domain.email.MailAuthEntity;
import com.juicycool.backend.domain.email.exception.ExpiredAuthCodeException;
import com.juicycool.backend.domain.email.repository.MailAuthRepository;
import com.juicycool.backend.domain.email.service.VerificationMailService;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@TransactionService
@RequiredArgsConstructor
public class VerificationMailServiceImpl implements VerificationMailService {

    private final MailAuthRepository mailAuthRepository;

    public void execute(String email, String authCode) {
        MailAuthEntity emailAuth = mailAuthRepository.findById(email)
                .orElseThrow(ExpiredAuthCodeException::new);

        if (!Objects.equals(emailAuth.getRandomValue(), authCode))
            throw new ExpiredAuthCodeException();

        MailAuthEntity updateEmailAuth = emailAuth.updateAuthentication();

        mailAuthRepository.save(updateEmailAuth);
    }
}
