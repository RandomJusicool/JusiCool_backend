package com.juicycool.backend.domain.stock.service.impl;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.Status;
import com.juicycool.backend.domain.reservation.repository.ReservationRepository;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.InvalidBuyingNumberException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.presentation.dto.request.BuyReservRequestDto;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.BuyReservationStockService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@TransactionService
@RequiredArgsConstructor
public class BuyReservationStockServiceImpl implements BuyReservationStockService {

    private final StockRepository stockRepository;
    private final UserUtil userUtil;
    private final ReservationRepository reservationRepository;

    public void execute(String stockCode, BuyReservRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Stock stock = stockRepository.findByCode(stockCode)
                .orElseThrow(NotFoundStockException::new);

        if (user.getPoints() - (dto.getGoal_price() * dto.getNum()) < 0)
            throw new InvalidBuyingNumberException();

        user.deductPoints(dto.getGoal_price() * dto.getNum());

        saveReservation(user, dto, stock);
    }

    private void saveReservation(User user, BuyReservRequestDto dto, Stock stock) {
        Reservation reservation = reservationRepository.findByUserAndStockCodeAndStatus(user, stock.getCode(), Status.BUY)
                .orElse(Reservation.builder()
                        .stock(stock)
                        .reservationPrice(dto.getGoal_price())
                        .status(Status.BUY)
                        .user(user)
                        .stockNum(0L)
                        .build());

        reservation.plusStockNum(dto.getNum());

        reservationRepository.save(reservation);


    }
}
