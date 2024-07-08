package com.juicycool.backend.domain.board.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetBoardInfoResponseDto {
    private String community_name;
    private String title;
    private String content;
    private Integer likes;

}
