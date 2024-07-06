package com.juicycool.backend.domain.reservation.scheduler;

import com.juicycool.backend.domain.reservation.service.ProcessingReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {

    private final ProcessingReservationService processingReservationService;

    @Scheduled(cron = "0 5-55/10 8-14 * * *", zone = "Asia/Seoul")
    @Scheduled(cron = "0 5,15,25,35 15 * * *", zone = "Asia/Seoul")
    public void processingReservation() {
        processingReservationService.execute();
    }


}
