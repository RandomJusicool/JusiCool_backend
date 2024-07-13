package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.receipt.Receipt;
import com.juicycool.backend.domain.receipt.repository.ReceiptRepository;
import com.juicycool.backend.domain.reservation.Status;
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
    private final ReceiptRepository receiptRepository;

    public void execute(Integer stockCode, BuyStockRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Stock stock = stockRepository.findById(stockCode)
                .orElseThrow(NotFoundStockException::new);

        Long allPoint = stock.getPresentPrice() * dto.getNum();

        if (allPoint > user.getPoints())
            throw new PointLowerThanPresentPriceException();

        saveOwnedStock(dto.getNum(), user, stock);
        user.deductPoints(allPoint);
        saveReceipt(user, stock, allPoint);
    }

    private void saveOwnedStock(Long number, User user, Stock stock) {
        OwnedStocks ownedStock = ownedStocksRepository.findByUserAndStock(user, stock)
                .orElse(OwnedStocks.builder()
                        .stockNumber(0L)
                        .points(0L)
                        .user(user)
                        .stock(stock)
                        .build());

        ownedStock.plusStockNum(number);
        ownedStock.plusPoints(stock.getPresentPrice() * number);

        ownedStocksRepository.save(ownedStock);
    }

    private void saveReceipt(User user, Stock stock, Long buyPoints) {
        Receipt receipt = Receipt.builder()
                .price(buyPoints)
                .status(Status.BUY)
                .stockName(stock.getName())
                .user(user)
                .build();

        receiptRepository.save(receipt);
    }
}
