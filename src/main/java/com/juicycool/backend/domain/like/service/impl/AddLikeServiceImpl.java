package com.juicycool.backend.domain.like.service.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.exception.NotFoundBoardException;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.like.Likes;
import com.juicycool.backend.domain.like.exception.AlreadyExistLikeException;
import com.juicycool.backend.domain.like.repository.LikeRepository;
import com.juicycool.backend.domain.like.service.AddLikeService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class AddLikeServiceImpl implements AddLikeService {

    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final UserUtil userUtil;

    public void execute(Long boardId) {
        User user = userUtil.getCurrentUser();

        Board board = boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);

        if (likeRepository.existsByBoardIdAndUserId(boardId, user.getId()))
            throw new AlreadyExistLikeException();

        saveLike(boardId, user.getId());
        board.addLikes();
    }

    private void saveLike(Long boardId, Long userId) {


        Likes like = Likes.builder()
                .boardId(boardId)
                .userId(userId)
                .build();

        likeRepository.save(like);
    }
}
