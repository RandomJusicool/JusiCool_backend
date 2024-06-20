package com.juicycool.backend.domain.board.service.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.exception.NotFoundBoardException;
import com.juicycool.backend.domain.board.presentation.dto.response.GetBoardInfoResponseDto;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.board.service.GetBoardInfoService;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetBoardInfoServiceImpl implements GetBoardInfoService {

    private final BoardRepository boardRepository;

    public GetBoardInfoResponseDto execute(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);

        return new GetBoardInfoResponseDto(board.getTitle(), board.getContent(), board.getLikes());

    }
}
