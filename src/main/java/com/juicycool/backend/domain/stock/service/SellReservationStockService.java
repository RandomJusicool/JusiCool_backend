package com.juicycool.backend.domain.stock.service;

import com.juicycool.backend.domain.stock.presentation.dto.request.SellReservRequestDto;

public interface SellReservationStockService {
    void execute(Long stockId, SellReservRequestDto dto);
}
