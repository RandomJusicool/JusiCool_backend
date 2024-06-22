package com.juicycool.backend.domain.reservation.service.impl;

import com.juicycool.backend.domain.reservation.presentation.dto.response.GetReservationListResponseDto;
import com.juicycool.backend.domain.reservation.repository.ReservationRepository;
import com.juicycool.backend.domain.reservation.service.GetReservationListService;
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

    public List<GetReservationListResponseDto> execute() {
        User user = userUtil.getCurrentUser();

        return reservationRepository.findByUser(user).stream()
                .map(reservation -> GetReservationListResponseDto.builder()
                        .stock_name(reservation.getStockName())
                        .stock_num(reservation.getStockNum())
                        .status(reservation.getStatus())
                        .build())
                .collect(Collectors.toList());

    }
}
