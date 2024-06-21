package com.juicycool.backend.domain.board.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class GetBoardListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String created_at;
    private Integer likes;
    private Integer comment_num;
}
