package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.exception.PointLowerThanPresentPriceException;
import com.juicycool.backend.domain.stock.presentation.dto.request.BuyStockRequestDto;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.BuyStockService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class BuyStockServiceImpl implements BuyStockService {

    private final StockRepository stockRepository;
    private final OwnedStocksRepository ownedStocksRepository;
    private final UserUtil userUtil;

    public void execute(Long stockId, BuyStockRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(NotFoundStockException::new);

        Long allPoint = stock.getPresentPrice() * dto.getNum();

        if (allPoint > user.getPoints())
            throw new PointLowerThanPresentPriceException();

        saveOwnedStock(dto.getNum(), user, stock);
        user.deductPoints(allPoint);
    }

    private void saveOwnedStock(Long number, User user, Stock stock) {
        OwnedStocks ownedStock = ownedStocksRepository.findByUserAndStock(user, stock)
                .orElse(OwnedStocks.builder()
                        .stockNumber(0L)
                        .user(user)
                        .stock(stock)
                        .build());

        ownedStock.plusStockNum(number);

        ownedStocksRepository.save(ownedStock);
    }
}
