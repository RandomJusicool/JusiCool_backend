package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.InvalidSellingNumberException;
import com.juicycool.backend.domain.stock.exception.NotFoundOwnedStockException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellStockRequestDto;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.SellStockService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class SellStockServiceImpl implements SellStockService {

    private final OwnedStocksRepository ownedStocksRepository;
    private final StockRepository stockRepository;
    private final UserUtil userUtil;

    public void execute(Long stockId, SellStockRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(NotFoundStockException::new);

        OwnedStocks ownedStocks = ownedStocksRepository.findByUserAndStock(user, stock)
                .orElseThrow(NotFoundOwnedStockException::new);

        ownedStocks.discountStockNumber(dto.getNum());

        if (ownedStocks.getStockNumber() == 0) {
            ownedStocksRepository.delete(ownedStocks);
        } else if (ownedStocks.getStockNumber() < 0) {
            throw new InvalidSellingNumberException();
        }

        user.addSellPoints(stock.getPresentPrice() * dto.getNum());
    }
}
