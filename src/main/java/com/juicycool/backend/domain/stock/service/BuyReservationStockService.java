package com.juicycool.backend.domain.stock.service;

import com.juicycool.backend.domain.stock.presentation.dto.request.BuyReservRequestDto;

public interface BuyReservationStockService {
    void execute(String stockCode, BuyReservRequestDto dto);
}
