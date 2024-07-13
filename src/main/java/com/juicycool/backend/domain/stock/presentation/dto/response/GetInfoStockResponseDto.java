package com.juicycool.backend.domain.stock.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetInfoStockResponseDto {
    private String name; // 주식 이름
    private String code; // 주식 코드
    private Long upDownPrice;
    private Double upDownPercent;
    private Long presentPrice; // 현재가
    private Long transactionVolume; // 거래량
    private Long transactionPrice; // 거래 대금
}
