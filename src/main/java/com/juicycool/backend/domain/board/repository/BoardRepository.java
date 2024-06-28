package com.juicycool.backend.domain.board.repository;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCommunity(Community community);
}
