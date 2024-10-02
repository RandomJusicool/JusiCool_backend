package com.juicycool.backend.stock.service.impl;

import com.juicycool.backend.domain.receipt.Receipt;
import com.juicycool.backend.domain.receipt.repository.ReceiptRepository;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.InvalidBuyingNumberException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.exception.PointLowerThanPresentPriceException;
import com.juicycool.backend.domain.stock.presentation.dto.request.BuyStockRequestDto;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.impl.BuyStockServiceImpl;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BuyStockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private OwnedStocksRepository ownedStocksRepository;

    @Mock
    private UserUtil userUtil;

    @Mock
    private ReceiptRepository receiptRepository;

    @InjectMocks
    private BuyStockServiceImpl buyStockService;

    private User mockUser;
    private Stock mockStock;
    private BuyStockRequestDto mockDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockUser = User.builder()
                .points(1000L)
                .build();

        mockStock = Stock.builder()
                .code("AAPL")
                .presentPrice(100L)
                .build();

        mockDto = new BuyStockRequestDto(5L);
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
    }

    @Test
    @DisplayName("주식 코드로 조회 시, 주식을 찾을 수 없는 경우")
    void If_you_cant_find_the_stock_when_you_look_it_up_stock_code() {
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundStockException.class, () -> buyStockService.execute("AAPL", mockDto));
    }

    @Test
    @DisplayName("매수하려는 갯수가 0개 이하인 경우")
    void If_the_number_of_purchases_to_be_made_is_zero_or_less() {
        mockDto = new BuyStockRequestDto(0L);

        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));

        assertThrows(InvalidBuyingNumberException.class, () -> buyStockService.execute("AAPL", mockDto));
    }

    @Test
    @DisplayName("매수하려는 주식의 총 가격보다 내 포인트가 적은 경우")
    void If_my_point_is_less_than_the_total_price_of_the_stock() {
        mockUser = User.builder().points(400L).build();
        mockStock = Stock.builder().presentPrice(500L).code("AAPL").build();

        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));

        assertThrows(PointLowerThanPresentPriceException.class, () -> buyStockService.execute("AAPL", mockDto));
    }


    @Test
    @DisplayName("정상적으로 주식이 매수되고 내역에 저장된 경우")
    void If_the_stock_id_normally_bought_and_stored () {
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(any(User.class), anyString()))
                .thenReturn(Optional.empty());

        buyStockService.execute("AAPL", mockDto);

        verify(ownedStocksRepository, times(1)).save(any(OwnedStocks.class));
        verify(receiptRepository, times(1)).save(any(Receipt.class));
    }
}
