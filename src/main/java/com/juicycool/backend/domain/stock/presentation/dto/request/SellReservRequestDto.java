package com.juicycool.backend.domain.stock.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SellReservRequestDto {
    private Long num;
    private Long goal_price;
}
