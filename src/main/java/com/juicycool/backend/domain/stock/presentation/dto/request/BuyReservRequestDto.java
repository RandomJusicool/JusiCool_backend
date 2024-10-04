package com.juicycool.backend.domain.stock.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BuyReservRequestDto {
    private Long num;
    private Long goal_price;
}
