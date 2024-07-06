package com.juicycool.backend.domain.receipt.presentation.dto.response;

import com.juicycool.backend.domain.reservation.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMyReceiptResponseDto {
    private String stockName;
    private Status status;
    private Long price;
}
