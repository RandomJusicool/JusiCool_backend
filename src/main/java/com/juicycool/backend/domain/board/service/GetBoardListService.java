package com.juicycool.backend.domain.board.service;

import com.juicycool.backend.domain.board.presentation.dto.response.GetBoardListResponseDto;

import java.util.List;

public interface GetBoardListService {
    List<GetBoardListResponseDto> execute(Long communityId);
}
