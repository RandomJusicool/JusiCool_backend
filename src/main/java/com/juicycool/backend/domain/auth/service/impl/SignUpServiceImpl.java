package com.juicycool.backend.domain.auth.service.impl;

import com.juicycool.backend.domain.auth.exception.DuplicateEmailException;
import com.juicycool.backend.domain.auth.presentation.dto.request.SignUpRequestDto;
import com.juicycool.backend.domain.auth.service.SignUpService;
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

    public void execute(SignUpRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new DuplicateEmailException();

        User user = User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .points(100000)
                .build();

        userRepository.save(user);

    }
}
