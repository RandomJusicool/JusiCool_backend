package com.juicycool.backend.domain.community.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCommunityListResponseDto {
    private Long id;
    private String name;
    private Integer board_num;
}
