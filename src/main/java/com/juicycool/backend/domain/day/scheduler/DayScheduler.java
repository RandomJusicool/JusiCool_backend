package com.juicycool.backend.domain.day.scheduler;

import com.juicycool.backend.domain.day.service.SaveDayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DayScheduler {

    private final SaveDayService saveDayService;

    @Scheduled(cron = "0 5-55/10 8-14 * * *", zone = "Asia/Seoul")
    @Scheduled(cron = "0 5,15,25,35 15 * * *", zone = "Asia/Seoul")
    public void saveDay() {
        log.info("--------------saveDay start--------------");
        saveDayService.saveDay();
        log.info("--------------saveDay end--------------");
    }
}
