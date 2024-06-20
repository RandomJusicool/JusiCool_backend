package com.juicycool.backend.domain.board.presentation;

import com.juicycool.backend.domain.board.presentation.dto.request.WriteBoardRequestDto;
import com.juicycool.backend.domain.board.service.WriteBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final WriteBoardService writeBoardService;

    @PostMapping("/{community_id}")
    public ResponseEntity<Void> writeBoard(
        @PathVariable("community_id") Long communityId,
        @RequestBody WriteBoardRequestDto dto
    ) {
        writeBoardService.execute(communityId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}