package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.receipt.Receipt;
import com.juicycool.backend.domain.receipt.repository.ReceiptRepository;
import com.juicycool.backend.domain.reservation.Status;
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
    private final ReceiptRepository receiptRepository;

    public void execute(String stockCode, SellStockRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Stock stock = stockRepository.findByCode(stockCode)
                .orElseThrow(NotFoundStockException::new);

        OwnedStocks ownedStocks = ownedStocksRepository.findByUserAndStockCode(user, stock.getCode())
                .orElseThrow(NotFoundOwnedStockException::new);

        if (dto.getNum() <= 0)
            throw new InvalidSellingNumberException();

        ownedStocks.discountStockNumber(dto.getNum());
        ownedStocks.minusPoints(stock.getPresentPrice() * dto.getNum());

        if (ownedStocks.getStockNumber() == 0) {
            ownedStocksRepository.delete(ownedStocks);
        } else if (ownedStocks.getStockNumber() < 0) {
            throw new InvalidSellingNumberException();
        }

        Long sellPoints = stock.getPresentPrice() * dto.getNum();

        user.addSellPoints(sellPoints);
        saveReceipt(user, stock, sellPoints);
    }

    private void saveReceipt(User user, Stock stock, Long buyPoints) {
        Receipt receipt = Receipt.builder()
                .price(buyPoints)
                .status(Status.SELL)
                .stockName(stock.getName())
                .user(user)
                .build();

        receiptRepository.save(receipt);
    }
}
