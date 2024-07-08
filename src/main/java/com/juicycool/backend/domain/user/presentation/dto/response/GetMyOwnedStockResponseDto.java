package com.juicycool.backend.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMyOwnedStockResponseDto {
    private String stock_name;
    private Long stock_num;
    private Long points;
    private Double upDownPercent;
    private Long upDownPoints;
}
