package com.juicycool.backend.domain.user.converter.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.user.converter.UserConverter;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyBoardResponseDto;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyOwnedStockResponseDto;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyPointResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {

    private final StockRepository stockRepository;

    public GetMyOwnedStockResponseDto toDto(OwnedStocks ownedStock) {
        Stock stock = stockRepository.findByCode(ownedStock.getStockCode())
                .orElseThrow(NotFoundStockException::new);

        return GetMyOwnedStockResponseDto.builder()
                .code(stock.getCode())
                .stock_name(stock.getName())
                .stock_num(ownedStock.getStockNumber())
                .points(ownedStock.getPoints() / ownedStock.getStockNumber())
                .upDownPercent((Math.floor(((double)(stock.getPresentPrice() - stock.getMarketPrice()) / stock.getMarketPrice()) * 100 * 10) / 10.0))
                .upDownPoints(stock.getPresentPrice() - stock.getMarketPrice())
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
