package com.juicycool.backend.domain.stock;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 주식 이름

    private Integer code; // 주식 코드

    private Integer marketPrice; // 시가

    private Integer highPrice; // 고가

    private Integer lowPrice; // 저가

    private Integer presentPrice; // 현재가

    private Integer contractPrice; // 체결가

    private Integer fluctuationComparedPreviousDay; // 전일 대비 등락

    private Long marketCapitalization; // 시가 총액

    private Integer transactionVolume; // 거래량

    private Integer transactionPrice; // 거래 대금



}
