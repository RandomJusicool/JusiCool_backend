package com.juicycool.backend.domain.user.service.impl;

import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.converter.UserConverter;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyPointResponseDto;
import com.juicycool.backend.domain.user.service.GetMyPointService;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetMyPointServiceImpl implements GetMyPointService {

    private final OwnedStocksRepository ownedStocksRepository;
    private final UserUtil userUtil;
    private final StockRepository stockRepository;
    private final UserConverter userConverter;

    public GetMyPointResponseDto execute() {
        User user = userUtil.getCurrentUser();

        List<OwnedStocks> ownedStocks = ownedStocksRepository.findByUser(user);

        Long nowStockPrice = 0L; // 현재 주식
        Long myStockPrice = 0L; // 보유 중인 주식

        for (OwnedStocks ownedStock: ownedStocks) {
            Stock stock = stockRepository.findByCode(ownedStock.getStockCode())
                    .orElseThrow(NotFoundStockException::new);

            nowStockPrice += stock.getPresentPrice() * ownedStock.getStockNumber();
            myStockPrice += ownedStock.getPoints();
        }

        myStockPrice += user.getPoints();

        Long myPoints = user.getPoints() + nowStockPrice;
        Long priceDifference = nowStockPrice - myStockPrice;

        double percent;

        if (priceDifference != 0) {
            percent = (double) priceDifference / myStockPrice * 100;
        } else {
            percent = 0D;
        }

        percent = Math.floor(percent);

        return userConverter.toPointDto(myPoints, priceDifference, percent);
    }
}
