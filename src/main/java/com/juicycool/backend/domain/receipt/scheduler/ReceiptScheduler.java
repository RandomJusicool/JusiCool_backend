package com.juicycool.backend.domain.receipt.scheduler;

import com.juicycool.backend.domain.receipt.service.DeleteAllReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiptScheduler {

    private final DeleteAllReceiptService deleteAllReceiptService;

    @Scheduled(cron = "0 0 0 1 * *")
    public void deleteAllReceipt() {
        deleteAllReceiptService.execute();
    }
}
