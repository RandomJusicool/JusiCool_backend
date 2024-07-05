package com.juicycool.backend.domain.email.service.impl;

import com.juicycool.backend.domain.email.MailAuthEntity;
import com.juicycool.backend.domain.email.exception.AlreadyAuthenticatedMailException;
import com.juicycool.backend.domain.email.exception.FailSendMailException;
import com.juicycool.backend.domain.email.presentation.dto.request.SendMailRequestDto;
import com.juicycool.backend.domain.email.repository.MailAuthRepository;
import com.juicycool.backend.domain.email.service.SendMailService;
import com.juicycool.backend.global.annotation.TransactionService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@TransactionService
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    private final JavaMailSender mailSender;
    private final MailAuthRepository emailAuthRepository;
    private final SpringTemplateEngine templateEngine;
    public static final String EMAIL_SUBJECT = "JusiCool 이메일 인증";

    public void execute(SendMailRequestDto dto) {
        MailAuthEntity emailAuth = emailAuthRepository.findById(dto.getEmail())
                .orElse(MailAuthEntity.builder()
                        .email(dto.getEmail())
                        .randomValue(createCode())
                        .authentication(false)
                        .attemptCount(1)
                        .build());

        if (emailAuth.getAuthentication())
            throw new AlreadyAuthenticatedMailException();

        emailAuthRepository.save(emailAuth);
        sendEmail(dto.getEmail(), emailAuth.getRandomValue());
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

    public void sendEmail(String email, String authCode) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(email);
            helper.setSubject(EMAIL_SUBJECT);
            String context = setContext(authCode);
            helper.setText(context, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new FailSendMailException();
        }
    }

    private String setContext(String authCode) {
        String template = "certificationMailTemplate";
        Context context = new Context();

        context.setVariable("authenticationCode", authCode);

        return templateEngine.process(template, context);
    }

}
