package com.juicycool.backend.domain.board.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBoardRequestDto {
    private String title;
    private String content;
}
