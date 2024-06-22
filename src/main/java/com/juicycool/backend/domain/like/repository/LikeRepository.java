package com.juicycool.backend.domain.like.repository;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.like.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    void deleteByBoardId(Long boardId);
}
