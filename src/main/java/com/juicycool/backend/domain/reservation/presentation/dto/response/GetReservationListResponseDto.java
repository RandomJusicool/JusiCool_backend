package com.juicycool.backend.domain.reservation.presentation.dto.response;

import com.juicycool.backend.domain.reservation.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetReservationListResponseDto {
    private String stock_name;
    private Long stock_num;
    private Status status;
}
