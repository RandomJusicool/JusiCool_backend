package com.juicycool.backend.stock;

import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.converter.StockConverter;
import com.juicycool.backend.domain.stock.presentation.dto.response.GetListStockResponseDto;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.impl.GetListStockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetListStockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockConverter stockConverter;

    @InjectMocks
    private GetListStockServiceImpl getListStockService;

    private Stock stock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("만약 정상적으로 주식 리스트가 반환된 경우")
    void If_stock_list_returned_normally() {
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();

        List<Stock> stockList = Arrays.asList(stock1, stock2);

        GetListStockResponseDto dto1 = GetListStockResponseDto.builder().build();
        GetListStockResponseDto dto2 = GetListStockResponseDto.builder().build();

        List<GetListStockResponseDto> dtoList = Arrays.asList(dto1, dto2);

        when(stockRepository.findAll()).thenReturn(stockList);
        when(stockConverter.toListDto(stock1)).thenReturn(dto1);
        when(stockConverter.toListDto(stock2)).thenReturn(dto2);

        List<GetListStockResponseDto> result = getListStockService.execute();

        assertEquals(dtoList, result);
        verify(stockRepository, times(1)).findAll();
        verify(stockConverter, times(1)).toListDto(stock1);
        verify(stockConverter, times(1)).toListDto(stock2);
    }
}
