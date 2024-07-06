package com.juicycool.backend.domain.receipt.service;

import com.juicycool.backend.domain.receipt.presentation.dto.response.GetMyReceiptResponseDto;

import java.util.List;

public interface GetMtReceiptService {
    List<GetMyReceiptResponseDto> execute();
}
