package com.juicycool.backend.domain.stock.presentation;

import com.juicycool.backend.domain.stock.presentation.dto.request.BuyReservRequestDto;
import com.juicycool.backend.domain.stock.presentation.dto.request.BuyStockRequestDto;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellReservRequestDto;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellStockRequestDto;
import com.juicycool.backend.domain.stock.presentation.dto.response.GetInfoStockResponseDto;
import com.juicycool.backend.domain.stock.presentation.dto.response.GetListStockResponseDto;
import com.juicycool.backend.domain.stock.service.*;
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
    private final BuyReservationStockService buyReservationStockService;
    private final GetInfoStockService getInfoStockService;

    @PostMapping("/{stock_code}")
    public ResponseEntity<Void> buyStock(
        @PathVariable("stock_code") Integer stockCode,
        @RequestBody BuyStockRequestDto dto
    ) {
        buyStockService.execute(stockCode, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{stock_code}")
    public ResponseEntity<Void> sellStock(
        @PathVariable("stock_code") Integer stockCode,
        @RequestBody SellStockRequestDto dto
    ) {
        sellStockService.execute(stockCode, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<GetListStockResponseDto>> getListStock() {
        List<GetListStockResponseDto> response = getListStockService.execute();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sell/{stock_code}")
    public ResponseEntity<Void> sellReservationStock(
        @PathVariable("stock_code") Integer stockCode,
        @RequestBody SellReservRequestDto dto
    ) {
        sellReservationStockService.execute(stockCode, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/buy/{stock_code}")
    public ResponseEntity<Void> buyReservationStock(
        @PathVariable("stock_code") Integer stockCode,
        @RequestBody BuyReservRequestDto dto
    ) {
        buyReservationStockService.execute(stockCode, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{stock_code}")
    public ResponseEntity<GetInfoStockResponseDto> getInfoStock(@PathVariable("stock_code") Integer stockCode) {
        GetInfoStockResponseDto response = getInfoStockService.execute(stockCode);
        return ResponseEntity.ok(response);
    }
}
