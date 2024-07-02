package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.stock.presentation.dto.response.GetInfoStockResponseDto;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.GetInfoStockService;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetInfoStockServiceImpl implements GetInfoStockService {

    private final StockRepository stockRepository;

    public GetInfoStockResponseDto execute(Long stockId) {
        return stockRepository.findById(stockId)
                .map(stock -> GetInfoStockResponseDto.builder()
                        .name(stock.getName())
                        .code(stock.getCode())
                        .marketPrice(stock.getMarketPrice())
                        .headPrice(stock.getHeadPrice())
                        .highPrice(stock.getHighPrice())
                        .lowPrice(stock.getLowPrice())
                        .presentPrice(stock.getPresentPrice())
                        .contractPrice(stock.getContractPrice())
                        .fluctuationComparedPreviousDay(stock.getFluctuationComparedPreviousDay())
                        .transactionVolume(stock.getTransactionVolume())
                        .transactionPrice(stock.getTransactionPrice())
                        .upDownPercent((long)(((double)(stock.getHeadPrice() - stock.getMarketPrice()) / stock.getMarketPrice()) * 100))
                        .upDownPrice(stock.getHeadPrice() - stock.getMarketPrice())
                ).get().build();
    }
}
