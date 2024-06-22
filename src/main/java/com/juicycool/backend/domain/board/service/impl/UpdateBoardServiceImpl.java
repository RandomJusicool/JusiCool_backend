package com.juicycool.backend.domain.board.service.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.exception.NotFoundBoardException;
import com.juicycool.backend.domain.board.exception.NotMatchUserException;
import com.juicycool.backend.domain.board.presentation.dto.request.UpdateBoardRequestDto;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.board.service.UpdateBoardService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class UpdateBoardServiceImpl implements UpdateBoardService {

    private final BoardRepository boardRepository;
    private final UserUtil userUtil;

    public void execute(Long boardId, UpdateBoardRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);

        if (findBoard.getUser() != user)
            throw new NotMatchUserException();

        saveBoard(findBoard, dto);
    }

    private void saveBoard(Board board,UpdateBoardRequestDto dto) {
        Board updateBoard = Board.builder()
                .id(board.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .likes(board.getLikes())
                .commentNum(board.getCommentNum())
                .community(board.getCommunity())
                .user(board.getUser())
                .createdAt(board.getCreatedAt())
                .build();

        boardRepository.save(updateBoard);
    }
}
