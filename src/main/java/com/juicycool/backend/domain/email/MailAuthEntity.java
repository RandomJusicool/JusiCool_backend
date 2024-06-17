package com.juicycool.backend.domain.email;

import com.juicycool.backend.domain.email.presentation.dto.request.VerificationMailRequestDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refreshToken", timeToLive = 60)
@Builder
@Getter
public class MailAuthEntity {

    @Id
    private String email;
    @Indexed
    private String randomValue;
    private Boolean authentication;
    private Integer attemptCount;

    public MailAuthEntity updateAuthentication(Boolean authentication) {
        return MailAuthEntity.builder()
                .email(email)
                .randomValue(randomValue)
                .authentication(authentication)
                .attemptCount(attemptCount)
                .build();
    }

}
