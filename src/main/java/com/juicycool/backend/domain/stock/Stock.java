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

    private Integer market_price; // 시가

    private Integer high_price; // 고가

    private Integer low_price; // 저가

    private Integer present_price; // 현재가

    private Integer contract_price; // 체결가

    private Integer fluctuation_compared_previous_day; // 전일 대비 등락

    private Integer market_capitalization; // 시가 총액

    private Integer transaction_volume; // 거래량

    private Integer transaction_price; // 거래 대금



}
