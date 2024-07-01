package com.juicycool.backend.domain.stock.presentation;

import com.juicycool.backend.domain.stock.presentation.dto.request.BuyStockRequestDto;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellStockRequestDto;
import com.juicycool.backend.domain.stock.presentation.dto.response.GetListStockResponseDto;
import com.juicycool.backend.domain.stock.service.BuyStockService;
import com.juicycool.backend.domain.stock.service.GetListStockService;
import com.juicycool.backend.domain.stock.service.SellReservationStockService;
import com.juicycool.backend.domain.stock.service.SellStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {

    private final BuyStockService buyStockService;
    private final SellStockService sellStockService;
    private final GetListStockService getListStockService;
    private final SellReservationStockService sellReservationStockService;

    @PostMapping("/{stock_id}")
    public ResponseEntity<Void> buyStock(
        @PathVariable("stock_id") Long stockId,
        @RequestBody BuyStockRequestDto dto
    ) {
        buyStockService.execute(stockId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{stock_id}")
    public ResponseEntity<Void> sellStock(
        @PathVariable("stock_id") Long stockId,
        @RequestBody SellStockRequestDto dto
    ) {
        sellStockService.execute(stockId, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<GetListStockResponseDto>> getListStock() {
        List<GetListStockResponseDto> response = getListStockService.execute();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sell/{stock_id}")
    public ResponseEntity<Void> sellReservationStock(
        @PathVariable("stock_id") Long stockId,
        @RequestBody SellStockRequestDto dto
    ) {
        sellReservationStockService.execute(stockId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
