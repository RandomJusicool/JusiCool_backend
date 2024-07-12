package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.stock.converter.StockConverter;
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
    private final StockConverter stockConverter;

    public List<GetListStockResponseDto> execute() {
        return stockRepository.findAll().stream()
                .map(stockConverter::toDto)
                .collect(Collectors.toList());
    }
}
