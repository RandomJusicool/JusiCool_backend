package com.juicycool.backend.domain.stock.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetInfoStockResponseDto {
    private String name; // 주식 이름
    private Integer code; // 주식 코드
    private Long upDownPrice;
    private Long upDownPercent;
    private Long marketPrice; // 시가
    private Long headPrice; // 종가
    private Long highPrice; // 고가
    private Long lowPrice; // 저가
    private Long presentPrice; // 현재가
    private Long contractPrice; // 체결가
    private Long fluctuationComparedPreviousDay; // 전일 대비 등락
    private Long transactionVolume; // 거래량
    private Long transactionPrice; // 거래 대금
}
