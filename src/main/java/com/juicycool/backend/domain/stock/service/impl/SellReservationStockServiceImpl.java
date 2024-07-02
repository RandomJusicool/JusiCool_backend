package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.Status;
import com.juicycool.backend.domain.reservation.repository.ReservationRepository;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.NotFoundOwnedStockException;
import com.juicycool.backend.domain.stock.exception.NotFoundReservationException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.exception.PointLowerThanPresentPriceException;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellReservRequestDto;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellStockRequestDto;
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

    public void execute(Long stockId, SellReservRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(NotFoundStockException::new);

        OwnedStocks ownedStocks = ownedStocksRepository.findByUserAndStock(user, stock)
                .orElseThrow(NotFoundOwnedStockException::new);

        if (ownedStocks.getStockNumber() - dto.getNum() < 0)
            throw new PointLowerThanPresentPriceException();

        saveReservation(stock, dto, user);
    }

    private void saveReservation(Stock stock, SellReservRequestDto dto, User user) {
        if (reservationRepository.existsByUserAndStockCode(user, stock.getCode())) {
//            Reservation reservation = reservationRepository.findByUserAndStockCode(user, stock.getCode())
//                    .orElseThrow(NotFoundReservationException::new);
//
//            reservation.plusStockNum(dto.getNum());
        } else {
            Reservation reservation = Reservation.builder()
                    .stockCode(stock.getCode())
                    .stockName(stock.getName())
                    .stockNum(dto.getNum())
                    .reservationPrice(dto.getGoal_price())
                    .status(Status.SELL)
                    .user(user)
                    .build();

            reservationRepository.save(reservation);
        }
    }
}
