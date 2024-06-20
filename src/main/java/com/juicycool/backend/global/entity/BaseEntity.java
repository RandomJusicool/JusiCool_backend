package com.juicycool.backend.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    private LocalDate createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDate.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime());
    }
}
