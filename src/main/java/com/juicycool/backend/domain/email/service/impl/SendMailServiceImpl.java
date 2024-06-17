package com.juicycool.backend.domain.email.service.impl;

import com.juicycool.backend.domain.email.EmailAuthEntity;
import com.juicycool.backend.domain.email.exception.AlreadyAuthenticatedMailException;
import com.juicycool.backend.domain.email.presentation.dto.request.SendMailRequestDto;
import com.juicycool.backend.domain.email.repository.MailAuthRepository;
import com.juicycool.backend.domain.email.service.SendMailService;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@TransactionService
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    private final JavaMailSender mailSender;
    private final MailAuthRepository emailAuthRepository;

    public void execute(SendMailRequestDto dto) {
        EmailAuthEntity emailAuth = emailAuthRepository.findById(dto.getEmail())
                .orElse(EmailAuthEntity.builder()
                        .email(dto.getEmail())
                        .randomValue(createCode())
                        .authentication(false)
                        .attemptCount(1)
                        .build());

        if (emailAuth.getAuthentication())
            throw new AlreadyAuthenticatedMailException();

        emailAuthRepository.save(emailAuth);
        sendCode(dto.getEmail(), emailAuth.getRandomValue());
    }

    private String createCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }

    private void sendCode(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[JusiCool] 이메일 인증");
        message.setText(code);

        mailSender.send(message);
    }

}
