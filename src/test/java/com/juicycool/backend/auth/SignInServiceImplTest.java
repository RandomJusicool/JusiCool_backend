package com.juicycool.backend.auth;

import com.juicycool.backend.domain.auth.RefreshToken;
import com.juicycool.backend.domain.auth.exception.NotMatchPasswordException;
import com.juicycool.backend.domain.auth.presentation.dto.request.SignInRequestDto;
import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.juicycool.backend.domain.auth.repository.RefreshTokenRepository;
import com.juicycool.backend.domain.auth.service.impl.SignInServiceImpl;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.exception.NotFoundUserException;
import com.juicycool.backend.domain.user.repository.UserRepository;
import com.juicycool.backend.global.security.jwt.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SignInServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private SignInServiceImpl signInService;

    private User mockUser;
    private SignInRequestDto dto;
    private TokenResponse tokenDto;
    private RefreshToken mockRefreshToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockUser = User.builder()
                .email("s23013@gsm.hs.kr")
                .password(passwordEncoder.encode("12345678!"))
                .build();

        dto = new SignInRequestDto("s23013@gsm.hs.kr", "12345678!");

        tokenDto = TokenResponse.builder()
                .accessToken("1231232")
                .refreshToken("123213")
                .build();

        mockRefreshToken = RefreshToken.builder()
                .email("s23013@gsm.hs.kr")
                .token("123213")
                .build();

        when(jwtProvider.generateTokenDto(any())).thenReturn(tokenDto);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));
        when(refreshTokenRepository.save(any())).thenReturn(mockRefreshToken);
    }

    @Test
    @DisplayName("만약 email로 유저를 찾지 못했을 경우")
    void If_cant_find_user_by_email() {
        dto = new SignInRequestDto("s54023@gsm.hs.kr", "12345698!");

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        assertThrows(NotFoundUserException.class, () -> signInService.execute(dto));
    }

    @Test
    @DisplayName("만약 password가 일치하지 않을 경우")
    void If_password_not_match() {
        dto = new SignInRequestDto("s23013@gsm.hs.kr", "12345679!");

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(dto.getPassword(), mockUser.getPassword())).thenReturn(false);

        assertThrows(NotMatchPasswordException.class, () -> signInService.execute(dto));
    }

    @Test
    @DisplayName("만약 정상적으로 로그인을 성공한 경우")
    void If_successfully_sign_in() {
        when(passwordEncoder.matches(dto.getPassword(), mockUser.getPassword())).thenReturn(true);

        signInService.execute(dto);

        verify(refreshTokenRepository, times(1)).save(any());
    }

}
