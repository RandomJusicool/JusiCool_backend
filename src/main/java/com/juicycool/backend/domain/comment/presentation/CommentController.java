package com.juicycool.backend.domain.comment.presentation;

import com.juicycool.backend.domain.comment.presentation.dto.request.WriteCommentRequestDto;
import com.juicycool.backend.domain.comment.service.WriteCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final WriteCommentService writeCommentService;

    @PostMapping("/{board_id}")
    public ResponseEntity<Void> writeComment(
        @PathVariable("board_id") Long boardId,
        @Valid @RequestBody WriteCommentRequestDto dto
    ) {
        writeCommentService.execute(boardId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
