package com.juicycool.backend.user;

import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.converter.UserConverter;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyBoardResponseDto;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyOwnedStockResponseDto;
import com.juicycool.backend.domain.user.service.impl.GetMyOwnedStockServiceImpl;
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

public class GetMyOwnedStockServiceImplTest {

    @Mock
    private OwnedStocksRepository ownedStocksRepository;

    @Mock
    private UserUtil userUtil;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private GetMyOwnedStockServiceImpl getMyOwnedStockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("만약 내가 가지고 있는 주식 리스트가 불러와진 경우")
    void If_list_of_share_have_is_imported() {
        User user = new User();

        when(userUtil.getCurrentUser()).thenReturn(user);

        OwnedStocks ownedStocks1 = new OwnedStocks();
        OwnedStocks ownedStocks2 = new OwnedStocks();

        List<OwnedStocks> ownedStocksList = Arrays.asList(ownedStocks1, ownedStocks2);

        GetMyOwnedStockResponseDto dto1 = GetMyOwnedStockResponseDto.builder().build();
        GetMyOwnedStockResponseDto dto2 = GetMyOwnedStockResponseDto.builder().build();

        List<GetMyOwnedStockResponseDto> dtoList = Arrays.asList(dto1, dto2);

        when(ownedStocksRepository.findByUser(user)).thenReturn(ownedStocksList);
        when(userConverter.toDto(ownedStocks1)).thenReturn(dto1);
        when(userConverter.toDto(ownedStocks2)).thenReturn(dto2);

        List<GetMyOwnedStockResponseDto> result = getMyOwnedStockService.execute();

        assertEquals(result, dtoList);
        when(ownedStocksRepository.findByUser(user)).thenReturn(ownedStocksList);
        verify(userUtil, times(1)).getCurrentUser();
        verify(ownedStocksRepository, times(1)).findByUser(user);
        verify(userConverter, times(1)).toDto(ownedStocks1);
        verify(userConverter, times(1)).toDto(ownedStocks2);
    }
}
