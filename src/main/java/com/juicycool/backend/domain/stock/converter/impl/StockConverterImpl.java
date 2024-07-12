package com.juicycool.backend.domain.stock.converter.impl;

import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.converter.StockConverter;
import com.juicycool.backend.domain.stock.presentation.dto.response.GetInfoStockResponseDto;
import com.juicycool.backend.domain.stock.presentation.dto.response.GetListStockResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StockConverterImpl implements StockConverter {

    public GetListStockResponseDto toListDto(Stock stock) {
        return GetListStockResponseDto.builder()
                .id(stock.getId())
                .name(stock.getName())
                .presentPrice(stock.getPresentPrice())
                .upDownPercent((Math.floor(((double)(stock.getPresentPrice() - stock.getMarketPrice()) / stock.getMarketPrice()) * 100 * 10) / 10.0))
                .upDownPrice(stock.getPresentPrice() - stock.getMarketPrice())
                .build();
    }

    public GetInfoStockResponseDto toInfoDto(Stock stock) {
        return GetInfoStockResponseDto.builder()
                .name(stock.getName())
                .code(stock.getCode())
                .presentPrice(stock.getPresentPrice())
                .transactionVolume(stock.getTransactionVolume())
                .transactionPrice(stock.getTransactionPrice())
                .upDownPercent((Math.floor(((double)(stock.getPresentPrice() - stock.getMarketPrice()) / stock.getMarketPrice()) * 100 * 10) / 10.0))
                .upDownPrice(stock.getPresentPrice() - stock.getMarketPrice())
                .build();
    }

}
