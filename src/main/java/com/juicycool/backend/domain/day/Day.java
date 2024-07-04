package com.juicycool.backend.domain.day;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code; // 주식 코드

    private Long marketPrice; // 시가

    private Long highPrice; // 고가

    private Long lowPrice; // 저가

    private Long presentPrice; // 현재가

    private Long headPrice; // 종가

    private Long volume; // 거래량

    private Long upDownPercent; // 등략률
}
