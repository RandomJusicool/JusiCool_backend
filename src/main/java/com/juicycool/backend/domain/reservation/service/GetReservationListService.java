package com.juicycool.backend.domain.reservation.service;

import com.juicycool.backend.domain.reservation.presentation.dto.response.GetReservationListResponseDto;

import java.util.List;

public interface GetReservationListService {
    List<GetReservationListResponseDto> execute();
}
