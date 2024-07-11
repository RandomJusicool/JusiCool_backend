package com.juicycool.backend.domain.auth.service.impl;

import com.juicycool.backend.domain.auth.exception.DuplicateEmailException;
import com.juicycool.backend.domain.auth.exception.NotVerificationMailException;
import com.juicycool.backend.domain.auth.presentation.dto.request.SignUpRequestDto;
import com.juicycool.backend.domain.auth.service.SignUpService;
import com.juicycool.backend.domain.email.MailAuthEntity;
import com.juicycool.backend.domain.email.repository.MailAuthRepository;
import com.juicycool.backend.domain.user.Authority;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.repository.UserRepository;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@TransactionService
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailAuthRepository mailAuthRepository;

    public void execute(SignUpRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            mailAuthRepository.deleteById(dto.getEmail());
            throw new DuplicateEmailException();
        }

        MailAuthEntity mailAuth = mailAuthRepository.findById(dto.getEmail())
                .orElseThrow(NotVerificationMailException::new);

        if (!mailAuth.getAuthentication())
            throw new NotVerificationMailException();

        User user = User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .authority(Authority.USER)
                .password(passwordEncoder.encode(dto.getPassword()))
                .points(10000000L)
                .build();

        mailAuthRepository.delete(mailAuth);

        userRepository.save(user);

    }
}
