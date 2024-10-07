package com.juicycool.backend.user;

import com.juicycool.backend.domain.board.Board;
import com.juicycool.backend.domain.board.repository.BoardRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.converter.UserConverter;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyBoardResponseDto;
import com.juicycool.backend.domain.user.service.impl.GetMyBoardServiceImpl;
import com.juicycool.backend.domain.user.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetMyBoardServiceImplTest {

    @Mock
    private UserUtil userUtil;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private GetMyBoardServiceImpl getMyBoardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("만약 자신이 쓴 게시물이 정상적으로 가져와진 경우")
    void If_post_you_write_imported_normally() {
        User user = new User();

        when(userUtil.getCurrentUser()).thenReturn(user);

        Board board1 = new Board();
        Board board2 = new Board();

        List<Board> boardList = Arrays.asList(board1, board2);

        GetMyBoardResponseDto dto1 = GetMyBoardResponseDto.builder().build();
        GetMyBoardResponseDto dto2 = GetMyBoardResponseDto.builder().build();

        List<GetMyBoardResponseDto> dtoList = Arrays.asList(dto1, dto2);

        when(boardRepository.findByUser(user)).thenReturn(boardList);
        when(userConverter.toBoardDto(board1)).thenReturn(dto1);
        when(userConverter.toBoardDto(board2)).thenReturn(dto2);

        List<GetMyBoardResponseDto> result = getMyBoardService.execute();

        assertEquals(dtoList, result);
        verify(userUtil, times(1)).getCurrentUser();
        verify(boardRepository, times(1)).findByUser(user);
        verify(userConverter, times(1)).toBoardDto(board1);
        verify(userConverter, times(1)).toBoardDto(board2);
    }
}
