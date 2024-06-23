package com.juicycool.backend.domain.stock.presentation;

import com.juicycool.backend.domain.stock.presentation.dto.request.BuyStockRequestDto;
import com.juicycool.backend.domain.stock.service.BuyStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {

    private final BuyStockService buyStockService;

    @PostMapping("/{stock_id}")
    public ResponseEntity<Void> buyStock(
        @PathVariable("stock_id") Long stockId,
        @RequestBody BuyStockRequestDto dto
    ) {
        buyStockService.execute(stockId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
