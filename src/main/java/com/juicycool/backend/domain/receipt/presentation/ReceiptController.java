package com.juicycool.backend.domain.receipt.presentation;

import com.juicycool.backend.domain.receipt.presentation.dto.response.GetMyReceiptResponseDto;
import com.juicycool.backend.domain.receipt.service.GetMtReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receipt")
public class ReceiptController {

    private final GetMtReceiptService getMtReceiptService;

    @GetMapping
    public ResponseEntity<List<GetMyReceiptResponseDto>> getMyReceipt() {
        List<GetMyReceiptResponseDto> response = getMtReceiptService.execute();
        return ResponseEntity.ok(response);
    }
}
