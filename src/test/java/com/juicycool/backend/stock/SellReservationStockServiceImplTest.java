package com.juicycool.backend.stock;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.repository.ReservationRepository;
import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.InvalidSellingNumberException;
import com.juicycool.backend.domain.stock.exception.NotFoundOwnedStockException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.exception.PointLowerThanPresentPriceException;
import com.juicycool.backend.domain.stock.presentation.dto.request.SellReservRequestDto;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.impl.SellReservationStockServiceImpl;
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

public class SellReservationStockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private OwnedStocksRepository ownedStocksRepository;

    @Mock
    private UserUtil userUtil;

    @InjectMocks
    private SellReservationStockServiceImpl sellReservationStockService;

    private User mockUser;
    private Stock mockStock;
    private OwnedStocks mockOwnedStocks;
    private SellReservRequestDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockUser = User.builder()
                .points(1000000L)
                .build();

        mockStock = Stock.builder()
                .code("1111")
                .presentPrice(10000L)
                .build();

        mockOwnedStocks = OwnedStocks.builder()
                .stockCode("1111")
                .stockNumber(3L)
                .points(30000L)
                .build();

        dto = new SellReservRequestDto(2L, 20000L);
    }

    @Test
    @DisplayName("만약 주식 코드로 주식을 찾지 못한 경우")
    void If_not_found_stock_by_stock_code() {
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundStockException.class, () -> sellReservationStockService.execute("1111", dto));
    }

    @Test
    @DisplayName("만약 보유 중인 주식이 없는 경우")
    void If_not_have_any_stock() {
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.empty());

        assertThrows(NotFoundOwnedStockException.class, () -> sellReservationStockService.execute("1111", dto));
    }

    @Test
    @DisplayName("만약 매도할 갯수가 1보다 작은 경우")
    void If_num_to_sell_is_less_than_one() {
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.of(mockOwnedStocks));

        dto = new SellReservRequestDto(0L, 20000L);

        assertThrows(InvalidSellingNumberException.class, () -> sellReservationStockService.execute("1111", dto));
    }

    @Test
    @DisplayName("만약 보유 중인 주식 갯수보다 더 많은 매도를 시도한 경우")
    void If_attempted_to_sell_more_than_num_of_have_stock() {
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.of(mockOwnedStocks));

        dto = new SellReservRequestDto(5L, 20000L);

        assertThrows(PointLowerThanPresentPriceException.class, () -> sellReservationStockService.execute("1111", dto));
    }

    @Test
    @DisplayName("만약 정상적으로 매도 예약을 성공한 경우")
    void If_reservation_for_sale_is_successful() {
        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
        when(ownedStocksRepository.findByUserAndStockCode(mockUser, mockStock.getCode())).thenReturn(Optional.of(mockOwnedStocks));

        sellReservationStockService.execute("1111", dto);

        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }
}
