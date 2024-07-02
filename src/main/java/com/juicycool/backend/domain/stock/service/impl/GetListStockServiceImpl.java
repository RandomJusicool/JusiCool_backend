package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.stock.presentation.dto.response.GetListStockResponseDto;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.GetListStockService;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetListStockServiceImpl implements GetListStockService {

    private final StockRepository stockRepository;

    public List<GetListStockResponseDto> execute() {
        return stockRepository.findAll().stream()
                .map(stock -> GetListStockResponseDto.builder()
                        .name(stock.getName())
                        .presentPrice(stock.getPresentPrice())
                        .upDownPercent((long)(((double)(stock.getHeadPrice() - stock.getMarketPrice()) / stock.getMarketPrice()) * 100))
                        .upDownPrice(stock.getHeadPrice() - stock.getMarketPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
