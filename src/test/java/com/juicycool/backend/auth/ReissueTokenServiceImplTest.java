package com.juicycool.backend.auth;

import com.juicycool.backend.domain.auth.RefreshToken;
import com.juicycool.backend.domain.auth.exception.ExpiredRefreshTokenException;
import com.juicycool.backend.domain.auth.exception.InvalidRefreshTokenException;
import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.juicycool.backend.domain.auth.repository.RefreshTokenRepository;
import com.juicycool.backend.domain.auth.service.impl.ReissueTokenServiceImpl;
import com.juicycool.backend.global.security.jwt.JwtProvider;
import com.juicycool.backend.global.security.jwt.TokenParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ReissueTokenServiceImplTest {

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private TokenParser tokenParser;

    @InjectMocks
    private ReissueTokenServiceImpl reissueTokenService;

    private RefreshToken mockRefreshToken;
    private TokenResponse tokenDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockRefreshToken = RefreshToken.builder()
                .email("s23013@gsm.hs.kr")
                .token("123213")
                .build();

        tokenDto = TokenResponse.builder()
                .accessToken("124214")
                .refreshToken("124124")
                .build();

        when(refreshTokenRepository.findByToken(anyString())).thenReturn(Optional.of(mockRefreshToken));
        when(jwtProvider.generateTokenDto(anyString())).thenReturn(tokenDto);
    }

    @Test
    @DisplayName("만약 refreshToken으로 엔티티를 찾지 못한 경우")
    void If_entity_not_found_refresh_token() {
        String refreshToken = "Bearer 002302";

        when(refreshTokenRepository.findByToken(refreshToken)).thenReturn(Optional.empty());

        assertThrows(ExpiredRefreshTokenException.class, () -> reissueTokenService.execute(refreshToken));
    }

    @Test
    @DisplayName("만약 찾은 엔티티의 토큰과 parse한 토큰이 일치하지 않은 경우 (if문 삭제 예정)")
    void If_token_in_found_entity_and_token_parse_not_match() {
        String refreshToken = "Bearer 12323321";

        when(tokenParser.parseRefreshToken(anyString())).thenReturn("12323321");
        when(refreshTokenRepository.findByToken(refreshToken)).thenReturn(Optional.of(mockRefreshToken));

        assertThrows(InvalidRefreshTokenException.class, () -> reissueTokenService.execute(refreshToken));
    }

    @Test
    @DisplayName("만약 토큰 재발급이 성공한 경우")
    void If_token_reissue_successful() {
        String refreshToken = "Bearer 123213";
        String parseRefreshToken = "123213";

        when(tokenParser.parseRefreshToken(anyString())).thenReturn(parseRefreshToken);
        when(refreshTokenRepository.findByToken(refreshToken)).thenReturn(Optional.of(mockRefreshToken));

        reissueTokenService.execute(refreshToken);

        verify(refreshTokenRepository, times(1)).save(any(RefreshToken.class));
    }
}
