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
    private String code; // 주식 코드

    private String name; // 주식 이름

    private Long marketPrice; // 시가

    private Long highPrice; // 고가

    private Long lowPrice; // 저가

    private Long presentPrice; // 현재가

    private Long transactionVolume; // 거래량

    private Long transactionPrice; // 거래 대금

    private String storeAt;
}
