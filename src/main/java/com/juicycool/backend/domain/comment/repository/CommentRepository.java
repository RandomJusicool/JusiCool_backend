package com.juicycool.backend.domain.comment.repository;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByBoard(Board board);
}
