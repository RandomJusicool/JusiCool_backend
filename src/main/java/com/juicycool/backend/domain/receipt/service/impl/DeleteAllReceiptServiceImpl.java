package com.juicycool.backend.domain.receipt.service.impl;

import com.juicycool.backend.domain.receipt.repository.ReceiptRepository;
import com.juicycool.backend.domain.receipt.service.DeleteAllReceiptService;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class DeleteAllReceiptServiceImpl implements DeleteAllReceiptService {

    private final ReceiptRepository receiptRepository;

    public void execute() {
        receiptRepository.deleteAll();
    }
}
