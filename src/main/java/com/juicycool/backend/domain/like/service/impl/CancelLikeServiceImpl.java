package com.juicycool.backend.domain.like.service.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.exception.NotFoundBoardException;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.like.Likes;
import com.juicycool.backend.domain.like.exception.NotFoundLikeException;
import com.juicycool.backend.domain.like.repository.LikeRepository;
import com.juicycool.backend.domain.like.service.CancelLikeService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class CancelLikeServiceImpl implements CancelLikeService {

    private final LikeRepository likeRepository;
    private final UserUtil userUtil;
    private final BoardRepository boardRepository;

    public void execute(Long boardId) {
        User user = userUtil.getCurrentUser();

        if (!likeRepository.existsByBoardIdAndUserId(boardId, user.getId()))
            throw new NotFoundLikeException();

        Board board = boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);

        likeRepository.deleteByUserId(user.getId());
        board.cancelLike();
    }
}
