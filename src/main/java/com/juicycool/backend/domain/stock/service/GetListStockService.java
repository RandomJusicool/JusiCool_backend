package com.juicycool.backend.domain.stock.service;

import com.juicycool.backend.domain.stock.presentation.dto.response.GetListStockResponseDto;

import java.util.List;

public interface GetListStockService {
    List<GetListStockResponseDto> execute();
}
