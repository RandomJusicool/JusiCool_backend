package com.juicycool.backend.domain.day.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetDayResponseDto {
    private Long marketPrice; // 시가
    private Long highPrice; // 고가
    private Long lowPrice; // 저가
    private Long presentPrice; // 현재가
    private Long volume; // 거래량
    private Double upDownPercent; // 등략률
    private String storeAt; // 저장된 시간
}
