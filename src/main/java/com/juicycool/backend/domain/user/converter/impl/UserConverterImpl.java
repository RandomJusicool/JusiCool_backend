package com.juicycool.backend.domain.user.converter.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.user.converter.UserConverter;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyBoardResponseDto;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyOwnedStockResponseDto;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyPointResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    public GetMyOwnedStockResponseDto toDto(OwnedStocks ownedStock) {
        return GetMyOwnedStockResponseDto.builder()
                .code(ownedStock.getStock().getCode())
                .stock_name(ownedStock.getStock().getName())
                .stock_num(ownedStock.getStockNumber())
                .points(ownedStock.getPoints() / ownedStock.getStockNumber())
                .upDownPercent((Math.floor(((double)(ownedStock.getStock().getPresentPrice() - ownedStock.getStock().getMarketPrice()) / ownedStock.getStock().getMarketPrice()) * 100 * 10) / 10.0))
                .upDownPoints(ownedStock.getStock().getPresentPrice() - ownedStock.getStock().getMarketPrice())
                .build();
    }

    public GetMyBoardResponseDto toBoardDto(Board board) {
        return GetMyBoardResponseDto.builder()
                .id(board.getId())
                .build();
    }

    public GetMyPointResponseDto toPointDto(Long allPoint, Long price, Double percent) {
        return GetMyPointResponseDto.builder()
                .points(allPoint)
                .upDownPrice(price)
                .upDownPercent(percent)
                .build();
    }

}
