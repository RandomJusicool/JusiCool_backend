package com.juicycool.backend.domain.stock.service;

import com.juicycool.backend.domain.stock.presentation.dto.request.BuyStockRequestDto;

public interface BuyStockService {
    void execute(String stockCode ,BuyStockRequestDto dto);
}
