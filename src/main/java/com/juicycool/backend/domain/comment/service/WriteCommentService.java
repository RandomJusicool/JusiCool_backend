package com.juicycool.backend.domain.comment.service;

import com.juicycool.backend.domain.comment.presentation.dto.request.WriteCommentRequestDto;

public interface WriteCommentService {
    void execute(Long boardId, WriteCommentRequestDto dto);
}
