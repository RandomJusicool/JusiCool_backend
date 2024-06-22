package com.juicycool.backend.domain.comment.service.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.exception.NotFoundBoardException;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.comment.Comment;
import com.juicycool.backend.domain.comment.presentation.dto.request.WriteCommentRequestDto;
import com.juicycool.backend.domain.comment.repository.CommentRepository;
import com.juicycool.backend.domain.comment.service.WriteCommentService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class WriteCommentServiceImpl implements WriteCommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserUtil userUtil;

    public void execute(Long boardId, WriteCommentRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Board board = boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);

        saveComment(dto.getContent(), boardId, user);
        board.addCommentNum();
    }

    private void saveComment(String content, Long boardId, User user) {
        Comment comment = Comment.builder()
                .boardId(boardId)
                .content(content)
                .user(user)
                .build();

        commentRepository.save(comment);
    }
}
