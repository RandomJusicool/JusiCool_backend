package com.juicycool.backend.auth;

import com.juicycool.backend.domain.auth.exception.DuplicateEmailException;
import com.juicycool.backend.domain.auth.exception.NotVerificationMailException;
import com.juicycool.backend.domain.auth.presentation.dto.request.SignUpRequestDto;
import com.juicycool.backend.domain.auth.service.impl.SignUpServiceImpl;
import com.juicycool.backend.domain.email.MailAuthEntity;
import com.juicycool.backend.domain.email.repository.MailAuthRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SignUpServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MailAuthRepository mailAuthRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private SignUpServiceImpl signUpService;

    private MailAuthEntity mockMailAuthEntity;
    private User mockUser;
    private SignUpRequestDto mockDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockUser = User.builder()
                .email("s23013@gsm.hs.kr")
                .build();

        mockMailAuthEntity = MailAuthEntity.builder()
                .email("s54001@gsm.hs.kr")
                .authentication(true)
                .build();

        mockDto = SignUpRequestDto.builder()
                .email("s54001@gsm.hs.kr")
                .password("12345678!")
                .build();
    }

    @Test
    @DisplayName("만약 이미 존재하는 이메일인 경우")
    void If_already_existing_email() {
        mockDto = SignUpRequestDto.builder()
                .email("s23013@gsm.hs.kr")
                .build();

        when(userRepository.existsByEmail(mockDto.getEmail())).thenReturn(true);

        assertThrows(DuplicateEmailException.class, () -> signUpService.execute(mockDto));
    }

    @Test
    @DisplayName("이메일 인증이 되지 않은 경우")
    void If_email_authentication_not_completed() {
        mockMailAuthEntity = MailAuthEntity.builder()
                .email("s54001@gsm.hs.kr")
                .authentication(false)
                .build();

        when(mailAuthRepository.findById(mockMailAuthEntity.getEmail())).thenReturn(Optional.of(mockMailAuthEntity));

        assertThrows(NotVerificationMailException.class, () -> signUpService.execute(mockDto));
    }

    @Test
    @DisplayName("정상적으로 회원가입이 완료된 경우")
    void If_sign_up_is_normally_registered() {
        when(userRepository.existsByEmail(mockDto.getEmail())).thenReturn(false);
        when(mailAuthRepository.findById(mockMailAuthEntity.getEmail())).thenReturn(Optional.of(mockMailAuthEntity));

        signUpService.execute(mockDto);

        verify(userRepository, times(1)).existsByEmail(mockDto.getEmail());
        verify(mailAuthRepository, times(1)).delete(mockMailAuthEntity);
    }
}
