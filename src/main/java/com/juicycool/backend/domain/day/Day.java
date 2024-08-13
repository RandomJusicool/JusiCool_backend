package com.juicycool.backend.domain.day;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code; // 주식 코드

    @Column(nullable = false)
    private Long marketPrice; // 시가

    @Column(nullable = false)
    private Long highPrice; // 고가

    @Column(nullable = false)
    private Long lowPrice; // 저가

    @Column(nullable = false)
    private Long presentPrice; // 현재가

    @Column(nullable = false)
    private Long volume; // 거래량

    @Column(nullable = false)
    private Double upDownPercent; // 등략률

    @Column(nullable = false)
    private String storeAt; // 저장된 시간
}
