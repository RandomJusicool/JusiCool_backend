package com.juicycool.backend.domain.stock.converter;

import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.presentation.dto.response.GetListStockResponseDto;

public interface StockConverter {
    GetListStockResponseDto toDto(Stock stock);
}
