package com.juicycool.backend.domain.reservation.service.impl;

import com.juicycool.backend.domain.receipt.Receipt;
import com.juicycool.backend.domain.receipt.repository.ReceiptRepository;
import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.Status;
import com.juicycool.backend.domain.reservation.repository.ReservationRepository;
import com.juicycool.backend.domain.reservation.service.ProcessingReservationService;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.InvalidSellingNumberException;
import com.juicycool.backend.domain.stock.exception.NotFoundOwnedStockException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@TransactionService
@RequiredArgsConstructor
public class ProcessingReservationServiceImpl implements ProcessingReservationService {

    private final ReservationRepository reservationRepository;
    private final StockRepository stockRepository;
    private final ReceiptRepository receiptRepository;
    private final OwnedStocksRepository ownedStocksRepository;

    public void execute() {
        List<Reservation> allReservation = reservationRepository.findAll();

        List<Stock> allStocks = stockRepository.findAll();

        for (Reservation reservation : allReservation) {
            Stock findReservationStock = allStocks.stream()
                    .filter(stock -> stock.getCode().equals(reservation.getStock().getCode()))
                    .findFirst()
                    .orElseThrow(NotFoundStockException::new);

            if (reservation.getReservationPrice() <= findReservationStock.getPresentPrice()) {
                if (reservation.getStatus() == Status.BUY) {
                    processBuyingStock(reservation, findReservationStock);
                } else if (reservation.getStatus() == Status.SELL) {
                    processSellingStock(reservation, findReservationStock);
                }
            }
        }
    }

    private void processBuyingStock(Reservation reservation, Stock stock) {
        saveOwnedStock(reservation.getStockNum(), reservation.getUser(), stock, reservation.getReservationPrice());

        saveReceipt(reservation.getUser(), stock, reservation.getReservationPrice() * reservation.getStockNum());

        reservationRepository.delete(reservation);
    }

    private void processSellingStock(Reservation reservation, Stock stock) {
        OwnedStocks ownedStocks = ownedStocksRepository.findByUserAndStock(reservation.getUser(), stock)
                .orElseThrow(NotFoundOwnedStockException::new);

        ownedStocks.discountStockNumber(reservation.getStockNum());
        ownedStocks.minusPoints(stock.getPresentPrice() * reservation.getStockNum());

        if (ownedStocks.getStockNumber() == 0) {
            ownedStocksRepository.delete(ownedStocks);
        } else if (ownedStocks.getStockNumber() < 0) {
            throw new InvalidSellingNumberException();
        }

        Long sellPoints = stock.getPresentPrice() * reservation.getStockNum();

        reservation.getUser().addSellPoints(sellPoints);

        saveReceipt(reservation.getUser(), stock, sellPoints);
    }

    private void saveOwnedStock(Long number, User user, Stock stock, Long reservationPrice) {
        OwnedStocks ownedStock = ownedStocksRepository.findByUserAndStock(user, stock)
                .orElse(OwnedStocks.builder()
                        .stockNumber(0L)
                        .user(user)
                        .stock(stock)
                        .build());

        ownedStock.plusStockNum(number);
        ownedStock.plusPoints(reservationPrice * number);

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