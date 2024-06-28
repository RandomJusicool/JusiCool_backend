package com.juicycool.backend.domain.board.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WriteBoardRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String content;
}
