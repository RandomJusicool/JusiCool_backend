package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.converter.StockConverter;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.presentation.dto.response.GetInfoStockResponseDto;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.GetInfoStockService;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetInfoStockServiceImpl implements GetInfoStockService {

    private final StockRepository stockRepository;
    private final StockConverter stockConverter;

    public GetInfoStockResponseDto execute(Integer stockCode) {
        Stock stock = stockRepository.findById(stockCode)
                .orElseThrow(NotFoundStockException::new);

        return stockConverter.toInfoDto(stock);
    }
}
