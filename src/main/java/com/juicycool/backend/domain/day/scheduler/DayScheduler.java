package com.juicycool.backend.domain.day.scheduler;

import com.juicycool.backend.domain.day.service.ClearDayService;
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
    private final ClearDayService clearDayService;

    @Scheduled(cron = "0 5/10 8-15 * * *", zone = "Asia/Seoul")
    public void saveDay() {
        log.info("--------------saveDay start--------------");
        saveDayService.saveDay();
        log.info("--------------saveDay end--------------");
    }

    @Scheduled(cron = "59 7 * * *", zone = "Asia/Seoul")
    public void clearDay() {
        log.info("--------------clearDay start--------------");
        clearDayService.execute();
        log.info("--------------clearDay end--------------");
    }
}
