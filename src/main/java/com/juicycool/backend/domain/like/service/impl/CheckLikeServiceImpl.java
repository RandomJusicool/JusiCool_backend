package com.juicycool.backend.domain.like.service.impl;

import com.juicycool.backend.domain.like.repository.LikeRepository;
import com.juicycool.backend.domain.like.service.CheckLikeService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class CheckLikeServiceImpl implements CheckLikeService {

    private final LikeRepository likeRepository;
    private final UserUtil userUtil;

    public Boolean execute(Long boardId) {
        User user = userUtil.getCurrentUser();

        if (likeRepository.existsByBoardIdAndUserId(boardId, user.getId())) {
            return true;
        } else {
            return false;
        }
    }
}
