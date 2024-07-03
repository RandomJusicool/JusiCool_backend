package com.juicycool.backend.domain.board.service.impl;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.presentation.dto.request.WriteBoardRequestDto;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.board.service.WriteBoardService;
import com.juicycool.backend.domain.community.Community;
import com.juicycool.backend.domain.community.exception.NotFoundCommunityException;
import com.juicycool.backend.domain.community.repository.CommunityRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@TransactionService
@RequiredArgsConstructor
@Slf4j
public class WriteBoardServiceImpl implements WriteBoardService {

    private final BoardRepository boardRepository;
    private final UserUtil userUtil;
    private final CommunityRepository communityRepository;

    public void execute(Long communityId, WriteBoardRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotFoundCommunityException::new);

        saveBoard(user, community, dto);
        community.plusBoardNum();
    }

    private void saveBoard(User user, Community community, WriteBoardRequestDto dto) {
        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .community(community)
                .createdAt(String.valueOf(LocalDate.now()))
                .commentNum(0)
                .likes(0)
                .build();

        boardRepository.save(board);
    }
}
