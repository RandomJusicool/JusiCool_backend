package com.juicycool.backend.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMyPointResponseDto {
    private Long points;
    private Double upDownPercent;
    private Long upDownPrice;
}
