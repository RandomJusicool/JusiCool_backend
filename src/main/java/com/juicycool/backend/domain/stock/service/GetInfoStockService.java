package com.juicycool.backend.domain.stock.service;

import com.juicycool.backend.domain.stock.presentation.dto.response.GetInfoStockResponseDto;

public interface GetInfoStockService {
    GetInfoStockResponseDto execute(String stockCode);
}
