package com.juicycool.backend.domain.board.service;

public interface DeleteBoardService {
    void execute(Long communityId, Long boardId);
}
