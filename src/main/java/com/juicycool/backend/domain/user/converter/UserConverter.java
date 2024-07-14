package com.juicycool.backend.domain.user.converter;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyBoardResponseDto;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyOwnedStockResponseDto;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyPointResponseDto;

public interface UserConverter {
    GetMyOwnedStockResponseDto toDto(OwnedStocks ownedStock);
    GetMyBoardResponseDto toBoardDto(Board board);
    GetMyPointResponseDto toPointDto(Long allPoint, Long price, Double percent);
}
