package com.juicycool.backend.domain.board.service.impl;

import com.juicycool.backend.domain.board.presentation.dto.response.GetBoardListResponseDto;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.board.service.GetBoardListService;
import com.juicycool.backend.domain.community.Community;
import com.juicycool.backend.domain.community.exception.NotFoundCommunityException;
import com.juicycool.backend.domain.community.repository.CommunityRepository;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetBoardListServiceImpl implements GetBoardListService {

    private final BoardRepository boardRepository;
    private final CommunityRepository communityRepository;

    public List<GetBoardListResponseDto> execute(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotFoundCommunityException::new);

        return boardRepository.findByCommunity(community).stream()
                .map(board -> GetBoardListResponseDto.builder()
                        .id(board.getId())
                        .community_name(community.getName())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .created_at(String.valueOf(board.getCreatedAt()))
                        .name(board.getUser().getName())
                        .likes(board.getLikes())
                        .comment_num(board.getCommentNum())
                        .build())
                .collect(Collectors.toList());




    }
}
