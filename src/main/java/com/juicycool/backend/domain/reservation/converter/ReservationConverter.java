package com.juicycool.backend.domain.reservation.converter;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.presentation.dto.response.GetReservationListResponseDto;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationConverter {


    private final StockRepository stockRepository;

    public GetReservationListResponseDto toDto(Reservation reservation) {

        Stock stock = stockRepository.findByCode(reservation.getStockCode())
                .orElseThrow(NotFoundStockException::new);

        return GetReservationListResponseDto.builder()
                .stock_name(stock.getName())
                .stock_num(reservation.getStockNum())
                .status(reservation.getStatus())
                .build();
    }
}
