package com.juicycool.backend.domain.user.converter;

import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyOwnedStockResponseDto;

public interface UserConverter {
    GetMyOwnedStockResponseDto toDto(OwnedStocks ownedStock);
}
