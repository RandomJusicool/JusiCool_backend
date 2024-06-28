package com.juicycool.backend.domain.board.service;

import com.juicycool.backend.domain.board.presentation.dto.response.GetBoardInfoResponseDto;

public interface GetBoardInfoService {
    GetBoardInfoResponseDto execute(Long boardId);
}
