package com.juicycool.backend.domain.community.service.impl;

import com.juicycool.backend.domain.community.presentation.dto.response.GetCommunityListResponseDto;
import com.juicycool.backend.domain.community.repository.CommunityRepository;
import com.juicycool.backend.domain.community.service.GetCommunityService;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetCommunityServiceImpl implements GetCommunityService {

    private final CommunityRepository communityRepository;

    public List<GetCommunityListResponseDto> execute() {
        return communityRepository.findAll().stream()
                .map(community -> GetCommunityListResponseDto.builder()
                        .id(community.getId())
                        .name(community.getName())
                        .board_num(community.getBoardNum())
                        .build())
                .collect(Collectors.toList());
    }
}
