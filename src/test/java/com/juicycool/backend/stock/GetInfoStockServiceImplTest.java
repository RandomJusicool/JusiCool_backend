package com.juicycool.backend.stock;

import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.converter.StockConverter;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.impl.GetInfoStockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetInfoStockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockConverter stockConverter;

    @InjectMocks
    private GetInfoStockServiceImpl getInfoStockService;

    private Stock stock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        stock = Stock.builder()
                .code("1111")
                .presentPrice(11111L)
                .build();
    }

    @Test
    @DisplayName("만약 주식 코드로 주식을 찾지 못한 경우")
    void If_not_found_stock_by_stock_code() {
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundStockException.class, () -> getInfoStockService.execute(anyString()));
    }

    @Test
    @DisplayName("만약 정상적으로 주식의 정보를 가져온 경우")
    void If_info_of_stock_normally_imported() {
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(stock));

        getInfoStockService.execute(anyString());

        verify(stockRepository, times(1)).findByCode(anyString());
    }
}