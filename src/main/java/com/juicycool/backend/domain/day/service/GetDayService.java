package com.juicycool.backend.domain.day.service;

import com.juicycool.backend.domain.day.presentation.dto.response.GetDayResponseDto;

import java.util.List;

public interface GetDayService {
    List<GetDayResponseDto> execute(String stockCode);
}
