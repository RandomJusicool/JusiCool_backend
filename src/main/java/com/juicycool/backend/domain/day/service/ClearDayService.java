package com.juicycool.backend.domain.day.service;

import com.juicycool.backend.domain.day.Day;
import com.juicycool.backend.domain.day.repository.DayRepository;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@TransactionService
@RequiredArgsConstructor
public class ClearDayService {

    private final DayRepository dayRepository;

    public void execute() {
        List<Day> days = dayRepository.findAll();

        for (Day day : days) {
            dayRepository.delete(day);
        }
    }
}
