package com.juicycool.backend.domain.like.presentation;

import com.juicycool.backend.domain.like.service.AddLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeController {

    private final AddLikeService addLikeService;

    @PostMapping("/{board_id}")
    public ResponseEntity<Void> addLike(@PathVariable("board_id") Long boardId) {
        addLikeService.execute(boardId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
