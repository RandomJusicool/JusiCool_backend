package com.juicycool.backend.domain.user.service;

import com.juicycool.backend.domain.user.presentation.dto.response.GetMyBoardResponseDto;

import java.util.List;

public interface GetMyBoardService {
    List<GetMyBoardResponseDto> execute();
}
