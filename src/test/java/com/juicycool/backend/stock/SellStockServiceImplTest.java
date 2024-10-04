package com.juicycool.backend.stock;

import com.juicycool.backend.domain.receipt.Receipt;
import com.juicycool.backend.domain.receipt.repository.ReceiptRepository;
import com.juicycool.backend.domain.reservation.Status;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.InvalidSellingNumberException;
import com.juicycool.backend.domain.stock.exception.NotFoundOwnedStockException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellStockRequestDto;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.impl.SellStockServiceImpl;
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

public class SellStockServiceImplTest {

    @Mock
    private OwnedStocksRepository ownedStocksRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private UserUtil userUtil;

    @Mock
    private ReceiptRepository receiptRepository;

    @InjectMocks
    private SellStockServiceImpl sellStockService;

    private User mockUser;
    private Stock mockStock;
    private OwnedStocks mockOwnedStocks;
    private Receipt mockReceipt;
    private SellStockRequestDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockUser = User.builder()
                .points(1000000L)
                .build();

        mockStock = Stock.builder()
                .code("111")
                .presentPrice(10000L)
                .build();

        mockOwnedStocks = OwnedStocks.builder()
                .stockCode("111")
                .stockNumber(2L)
                .user(mockUser)
                .points(20000L)
                .build();

        dto = new SellStockRequestDto(2L);

        mockReceipt = Receipt.builder()
                .price(40000L)
                .status(Status.SELL)
                .user(mockUser)
                .build();

        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.of(mockOwnedStocks));
        when(receiptRepository.save(any())).thenReturn(mockReceipt);
    }

    @Test
    @DisplayName("만약 주식코드로 주식을 찾지 못한 경우")
    void If_stock_code_fail_to_find_the_stock() {
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundStockException.class, () -> sellStockService.execute("11222", dto));
    }

    @Test
    @DisplayName("만약 보유 중인 주식을 찾지 못한 경우")
    void If_not_found_owned_stock() {
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.empty());

        assertThrows(NotFoundOwnedStockException.class, () -> sellStockService.execute("11222", dto));
    }

    @Test
    @DisplayName("만약 매수할 갯수가 1보다 작은 경우")
    void If_num_to_sell_less_than_one() {
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.of(mockOwnedStocks));

        dto = new SellStockRequestDto(0L);

        assertThrows(InvalidSellingNumberException.class, () -> sellStockService.execute("11222", dto));
    }

    @Test
    @DisplayName("보유한 갯수보다 더 많은 매도 갯수를 시도한 경우")
    void If_attempted_to_sell_more_than_have() {
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.of(mockOwnedStocks));

        dto = new SellStockRequestDto(3L);

        assertThrows(InvalidSellingNumberException.class, () -> sellStockService.execute("11222", dto));
    }

    @Test
    @DisplayName("만약 정상적으로 매도 주문을 채결한 경우")
    void If_selling_order_normally_placed() {
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.of(mockOwnedStocks));

        sellStockService.execute("111", dto);

        verify(ownedStocksRepository, times(1)).findByUserAndStockCode(mockUser, mockStock.getCode());
        verify(receiptRepository, times(1)).save(any(Receipt.class));
    }
}
