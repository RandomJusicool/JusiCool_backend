package com.juicycool.backend.domain.community.service;

import com.juicycool.backend.domain.community.presentation.dto.response.GetCommunityListResponseDto;

import java.util.List;

public interface GetCommunityService {
    List<GetCommunityListResponseDto> execute();
}
