package com.juicycool.backend.domain.comment.service;

import com.juicycool.backend.domain.comment.presentation.dto.response.GetCommentResponseDto;

import java.util.List;

public interface GetCommentService {
    List<GetCommentResponseDto> execute(Long boardId);
}
