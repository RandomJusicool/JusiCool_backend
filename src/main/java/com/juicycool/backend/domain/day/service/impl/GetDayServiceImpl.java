package com.juicycool.backend.domain.day.service.impl;

import com.juicycool.backend.domain.day.presentation.dto.response.GetDayResponseDto;
import com.juicycool.backend.domain.day.repository.DayRepository;
import com.juicycool.backend.domain.day.service.GetDayService;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@TransactionService
@RequiredArgsConstructor
public class GetDayServiceImpl implements GetDayService {

    private final DayRepository dayRepository;

    public List<GetDayResponseDto> execute(Integer stockCode) {
        return dayRepository.findByCode(stockCode).stream()
                .map(day -> GetDayResponseDto.builder()
                        .marketPrice(day.getMarketPrice())
                        .highPrice(day.getHighPrice())
                        .lowPrice(day.getLowPrice())
                        .presentPrice(day.getPresentPrice())
                        .headPrice(day.getHeadPrice())
                        .volume(day.getVolume())
                        .upDownPercent(day.getUpDownPercent())
                        .storeAt(day.getStoreAt())
                        .build())
                .collect(Collectors.toList());
    }
}
