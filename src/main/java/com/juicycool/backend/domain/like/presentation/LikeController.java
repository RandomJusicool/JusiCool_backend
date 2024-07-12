package com.juicycool.backend.domain.like.presentation;

import com.juicycool.backend.domain.like.service.AddLikeService;
import com.juicycool.backend.domain.like.service.CancelLikeService;
import com.juicycool.backend.domain.like.service.CheckLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeController {

    private final AddLikeService addLikeService;
    private final CancelLikeService cancelLikeService;
    private final CheckLikeService checkLikeService;

    @PostMapping("/{board_id}")
    public ResponseEntity<Void> addLike(@PathVariable("board_id") Long boardId) {
        addLikeService.execute(boardId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{board_id}")
    public ResponseEntity<Void> cancelLike(@PathVariable("board_id") Long boardId) {
        cancelLikeService.execute(boardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{board_id}")
    public ResponseEntity<Boolean> checkLike(@PathVariable("board_id") Long boardId) {
        boolean response = checkLikeService.execute(boardId);
        return ResponseEntity.ok(response);
    }
}
