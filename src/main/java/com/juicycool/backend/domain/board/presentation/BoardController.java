package com.juicycool.backend.domain.board.presentation;

import com.juicycool.backend.domain.board.presentation.dto.request.UpdateBoardRequestDto;
import com.juicycool.backend.domain.board.presentation.dto.request.WriteBoardRequestDto;
import com.juicycool.backend.domain.board.presentation.dto.response.GetBoardInfoResponseDto;
import com.juicycool.backend.domain.board.presentation.dto.response.GetBoardListResponseDto;
import com.juicycool.backend.domain.board.service.GetBoardInfoService;
import com.juicycool.backend.domain.board.service.GetBoardListService;
import com.juicycool.backend.domain.board.service.UpdateBoardService;
import com.juicycool.backend.domain.board.service.WriteBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final WriteBoardService writeBoardService;
    private final GetBoardInfoService getBoardInfoService;
    private final GetBoardListService getBoardListService;
    private final UpdateBoardService updateBoardService;

    @PostMapping("/{community_id}")
    public ResponseEntity<Void> writeBoard(
        @PathVariable("community_id") Long communityId,
        @RequestBody WriteBoardRequestDto dto
    ) {
        writeBoardService.execute(communityId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{board_id}")
    public ResponseEntity<GetBoardInfoResponseDto> getBoardInfo(@PathVariable("board_id") Long boardId) {
        GetBoardInfoResponseDto response = getBoardInfoService.execute(boardId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/{community_id}")
    public ResponseEntity<List<GetBoardListResponseDto>> getBoardList(@PathVariable("community_id") Long communityId) {
        List<GetBoardListResponseDto> response = getBoardListService.execute(communityId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{board_id}")
    public ResponseEntity<Void> updateBoard(
        @PathVariable("board_id") Long boardId,
        @RequestBody UpdateBoardRequestDto dto
    ) {
        updateBoardService.execute(boardId, dto);
        return ResponseEntity.noContent().build();
    }
}
