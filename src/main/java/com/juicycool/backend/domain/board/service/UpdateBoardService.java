package com.juicycool.backend.domain.board.service;

import com.juicycool.backend.domain.board.presentation.dto.request.UpdateBoardRequestDto;

public interface UpdateBoardService {
    void execute(Long boardId, UpdateBoardRequestDto dto);
}
