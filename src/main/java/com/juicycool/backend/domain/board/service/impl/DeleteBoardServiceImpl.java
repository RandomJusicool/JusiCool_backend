package com.juicycool.backend.domain.board.service.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.exception.NotFoundBoardException;
import com.juicycool.backend.domain.board.exception.NotMatchUserException;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.board.service.DeleteBoardService;
import com.juicycool.backend.domain.comment.repository.CommentRepository;
import com.juicycool.backend.domain.like.repository.LikeRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class DeleteBoardServiceImpl implements DeleteBoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final UserUtil userUtil;

    public void execute(Long boardId) {
        User user = userUtil.getCurrentUser();

        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);

        if (user != findBoard.getUser())
            throw new NotMatchUserException();


        commentRepository.deleteByBoard(findBoard);
        likeRepository.deleteByBoard(findBoard);
        boardRepository.delete(findBoard);

    }
}
