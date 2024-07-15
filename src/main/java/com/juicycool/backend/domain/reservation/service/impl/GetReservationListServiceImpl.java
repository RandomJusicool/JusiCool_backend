package com.juicycool.backend.domain.reservation.service.impl;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.converter.ReservationConverter;
import com.juicycool.backend.domain.reservation.presentation.dto.response.GetReservationListResponseDto;
import com.juicycool.backend.domain.reservation.repository.ReservationRepository;
import com.juicycool.backend.domain.reservation.service.GetReservationListService;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetReservationListServiceImpl implements GetReservationListService {

    private final ReservationRepository reservationRepository;
    private final UserUtil userUtil;
    private final ReservationConverter reservationConverter;

    public List<GetReservationListResponseDto> execute() {
        User user = userUtil.getCurrentUser();

        List<Reservation> reservation = reservationRepository.findByUser(user);

        return reservation.stream()
                .map(reservationConverter::toDto)
                .collect(Collectors.toList());
    }
}
