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

        Long priceDifference = (stock.getPresentPrice() * ownedStock.getStockNumber()) - ownedStock.getPoints();

        return GetMyOwnedStockResponseDto.builder()
                .code(stock.getCode())
                .stock_name(stock.getName())
                .stock_num(ownedStock.getStockNumber())
                .points(stock.getPresentPrice() * ownedStock.getStockNumber())
                .upDownPercent(Math.floor(((double)priceDifference / ownedStock.getPoints()) * 100))
                .upDownPoints(stock.getPresentPrice() - (ownedStock.getPoints() / ownedStock.getStockNumber()))
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
