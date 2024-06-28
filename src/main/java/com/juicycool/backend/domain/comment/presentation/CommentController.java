package com.juicycool.backend.domain.comment.presentation;

import com.juicycool.backend.domain.comment.presentation.dto.request.WriteCommentRequestDto;
import com.juicycool.backend.domain.comment.presentation.dto.response.GetCommentResponseDto;
import com.juicycool.backend.domain.comment.service.GetCommentService;
import com.juicycool.backend.domain.comment.service.WriteCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final WriteCommentService writeCommentService;
    private final GetCommentService getCommentService;

    @PostMapping("/{board_id}")
    public ResponseEntity<Void> writeComment(
        @PathVariable("board_id") Long boardId,
        @Valid @RequestBody WriteCommentRequestDto dto
    ) {
        writeCommentService.execute(boardId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{board_id}")
    public ResponseEntity<List<GetCommentResponseDto>> getComment(@PathVariable("board_id") Long boardId) {
        List<GetCommentResponseDto> response = getCommentService.execute(boardId);
        return ResponseEntity.ok(response);
    }

}
