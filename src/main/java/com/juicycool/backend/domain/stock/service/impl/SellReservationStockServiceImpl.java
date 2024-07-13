package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.Status;
import com.juicycool.backend.domain.reservation.repository.ReservationRepository;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.InvalidSellingNumberException;
import com.juicycool.backend.domain.stock.exception.NotFoundOwnedStockException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.exception.PointLowerThanPresentPriceException;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellReservRequestDto;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.SellReservationStockService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class SellReservationStockServiceImpl implements SellReservationStockService {

    private final StockRepository stockRepository;
    private final ReservationRepository reservationRepository;
    private final OwnedStocksRepository ownedStocksRepository;
    private final UserUtil userUtil;

    public void execute(String stockCode, SellReservRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Stock stock = stockRepository.findById(stockCode)
                .orElseThrow(NotFoundStockException::new);

        OwnedStocks ownedStocks = ownedStocksRepository.findByUserAndStock(user, stock)
                .orElseThrow(NotFoundOwnedStockException::new);

        if (ownedStocks.getStockNumber() - dto.getNum() < 0)
            throw new PointLowerThanPresentPriceException();

        saveReservation(stock, dto, user);
    }

    private void saveReservation(Stock stock, SellReservRequestDto dto, User user) {
        Reservation reservation = reservationRepository.findByUserAndStockCodeAndStatus(user, stock.getCode(), Status.SELL)
                .orElse(Reservation.builder()
                        .stock(stock)
                        .reservationPrice(dto.getGoal_price())
                        .status(Status.SELL)
                        .user(user)
                        .stockNum(0L)
                        .build());

        OwnedStocks ownedStocks = ownedStocksRepository.findByUserAndStock(user, stock)
                .orElseThrow(NotFoundOwnedStockException::new);

        if (ownedStocks.getStockNumber() < reservation.getStockNum() + dto.getNum()) {
            throw new InvalidSellingNumberException();
        }

        reservation.plusStockNum(dto.getNum());

        reservationRepository.save(reservation);
    }
}
