package com.juicycool.backend.domain.receipt.service.impl;

import com.juicycool.backend.domain.receipt.presentation.dto.response.GetMyReceiptResponseDto;
import com.juicycool.backend.domain.receipt.repository.ReceiptRepository;
import com.juicycool.backend.domain.receipt.service.GetMtReceiptService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetMyReceiptServiceImpl implements GetMtReceiptService {

    private final ReceiptRepository receiptRepository;
    private final UserUtil userUtil;

    public List<GetMyReceiptResponseDto> execute() {
        User user = userUtil.getCurrentUser();

        return receiptRepository.findByUser(user).stream()
                .map(receipt -> GetMyReceiptResponseDto.builder()
                        .stockName(receipt.getStockName())
                        .price(receipt.getPrice())
                        .status(receipt.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
