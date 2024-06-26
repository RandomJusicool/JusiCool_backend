package com.juicycool.backend.domain.stock.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetListStockResponseDto {
    private String name;
    private Long presentPrice;
    private Long upDownPrice;
    private Long upDownPercent;
}
