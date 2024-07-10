package com.juicycool.backend.domain.day.presentation;

import com.juicycool.backend.domain.day.presentation.dto.response.GetDayResponseDto;
import com.juicycool.backend.domain.day.service.GetDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/day")
public class DayController {

    private final GetDayService getDayService;

    @GetMapping("/{stock_code}")
    public ResponseEntity<List<GetDayResponseDto>> getDay(@PathVariable("stock_code") Integer stockCode) {
        List<GetDayResponseDto> response = getDayService.execute(stockCode);
        return ResponseEntity.ok(response);
    }


}
