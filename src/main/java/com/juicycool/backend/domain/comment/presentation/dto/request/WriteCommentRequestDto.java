package com.juicycool.backend.domain.comment.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WriteCommentRequestDto {
    @NotNull
    private String content;
}
