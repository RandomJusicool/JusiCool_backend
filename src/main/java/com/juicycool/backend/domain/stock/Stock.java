package com.juicycool.backend.domain.stock;

import jakarta.persistence.*;
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

    @Column(unique = true, nullable = false)
    private String code; // 주식 코드

    @Column(nullable = false)
    private String name; // 주식 이름

    @Column(nullable = false)
    private Long marketPrice; // 시가

    @Column(nullable = false)
    private Long highPrice; // 고가

    @Column(nullable = false)
    private Long lowPrice; // 저가

    @Column(nullable = false)
    private Long presentPrice; // 현재가

    @Column(nullable = false)
    private Long transactionVolume; // 거래량

    @Column(nullable = false)
    private Long transactionPrice; // 거래 대금

    @Column(nullable = false)
    private String storeTime;
}
