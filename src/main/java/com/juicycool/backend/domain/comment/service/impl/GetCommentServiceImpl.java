package com.juicycool.backend.domain.comment.service.impl;

import com.juicycool.backend.domain.board.exception.NotFoundBoardException;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.comment.presentation.dto.response.GetCommentResponseDto;
import com.juicycool.backend.domain.comment.repository.CommentRepository;
import com.juicycool.backend.domain.comment.service.GetCommentService;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetCommentServiceImpl implements GetCommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public List<GetCommentResponseDto> execute(Long boardId) {
        if (!boardRepository.existsById(boardId))
            throw new NotFoundBoardException();

        return commentRepository.findByBoardId(boardId).stream()
                .map(comment -> GetCommentResponseDto.builder()
                        .name(comment.getUser().getName())
                        .content(comment.getContent())
                        .build())
                .collect(Collectors.toList());
    }
}
