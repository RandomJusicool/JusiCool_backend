package com.juicycool.backend.domain.stock.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellReservRequestDto {
    private Long num;
    private Long goal_price;
}
