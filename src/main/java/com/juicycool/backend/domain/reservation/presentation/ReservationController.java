package com.juicycool.backend.domain.reservation.presentation;


import com.juicycool.backend.domain.reservation.presentation.dto.response.GetReservationListResponseDto;
import com.juicycool.backend.domain.reservation.service.GetReservationListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final GetReservationListService getReservationListService;

    @GetMapping
    public ResponseEntity<List<GetReservationListResponseDto>> getReservationStock() {
        List<GetReservationListResponseDto> response = getReservationListService.execute();
        return ResponseEntity.ok(response);
    }

}
