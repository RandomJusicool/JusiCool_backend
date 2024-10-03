package com.juicycool.backend.auth;

import com.juicycool.backend.domain.auth.RefreshToken;
import com.juicycool.backend.domain.auth.repository.RefreshTokenRepository;
import com.juicycool.backend.domain.auth.service.impl.LogoutServiceImpl;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class LogoutServiceImplTest {

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private UserUtil userUtil;

    @InjectMocks
    private LogoutServiceImpl logoutService;

    private User user;
    private RefreshToken refreshToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .email("s23013@gsm.hs.kr")
                .build();

        refreshToken = RefreshToken.builder()
                .email("s23013@gsm.hs.kr")
                .build();

        when(userUtil.getCurrentUser()).thenReturn(user);
        when(refreshTokenRepository.findByToken(anyString())).thenReturn(Optional.of(refreshToken));
    }

    @Test
    @DisplayName("만약 로그아웃이 성공한 경우")
    void If_logout_successful() {
        logoutService.execute();

        verify(refreshTokenRepository, times(1)).deleteById(user.getEmail());
    }

}
