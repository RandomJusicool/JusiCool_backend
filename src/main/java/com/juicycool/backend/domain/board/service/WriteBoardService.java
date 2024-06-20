package com.juicycool.backend.domain.board.service;

import com.juicycool.backend.domain.board.presentation.dto.request.WriteBoardRequestDto;

public interface WriteBoardService {
    void execute(Long communityId, WriteBoardRequestDto dto);
}
