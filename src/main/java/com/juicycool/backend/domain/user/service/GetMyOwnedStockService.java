package com.juicycool.backend.domain.user.service;

import com.juicycool.backend.domain.user.presentation.dto.response.GetMyOwnedStockResponseDto;

import java.util.List;

public interface GetMyOwnedStockService {
    List<GetMyOwnedStockResponseDto> execute();
}
