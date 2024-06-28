package com.juicycool.backend.domain.email;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "mailauth", timeToLive = 300)
@Builder
@Getter
public class MailAuthEntity {

    @Id
    private String email;
    @Indexed
    private String randomValue;
    private Boolean authentication;
    private Integer attemptCount;

    public MailAuthEntity updateAuthentication() {
        return MailAuthEntity.builder()
                .email(email)
                .randomValue(randomValue)
                .authentication(true)
                .attemptCount(attemptCount)
                .build();
    }

}
