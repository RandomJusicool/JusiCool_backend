package com.juicycool.backend.domain.user.service.impl;

import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.converter.UserConverter;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyBoardResponseDto;
import com.juicycool.backend.domain.user.service.GetMyBoardService;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetMyBoardServiceImpl implements GetMyBoardService {

    private final UserUtil userUtil;
    private final BoardRepository boardRepository;
    private final UserConverter userConverter;

    public List<GetMyBoardResponseDto> execute() {
        User user = userUtil.getCurrentUser();

        return boardRepository.findByUser(user).stream()
                .map(userConverter::toBoardDto)
                .collect(Collectors.toList());
    }
}
