package com.juicycool.backend.domain.auth;

import com.juicycool.backend.global.security.jwt.JwtProvider;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "jusi_refreshToken", timeToLive = JwtProvider.REFRESH_TOKEN_TIME)
@Builder
@Getter
public class RefreshToken {
    @Id
    @Indexed
    private Long userId;
    @Indexed
    private String token;
}
