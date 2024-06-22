package com.juicycool.backend.domain.comment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCommentResponseDto {
    private String name;
    private String content;
}
