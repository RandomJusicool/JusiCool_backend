package com.juicycool.backend.domain.like.service.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.exception.NotFoundBoardException;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.like.Likes;
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
        Board board = boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);

        saveLike(boardId);
        board.addLikes();
    }

    private void saveLike(Long boardId) {
        User user = userUtil.getCurrentUser();

        Likes like = Likes.builder()
                .boardId(boardId)
                .userId(user.getId())
                .build();

        likeRepository.save(like);
    }
}
