package com.juicycool.backend.stock;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.repository.ReservationRepository;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.exception.InvalidBuyingNumberException;
import com.juicycool.backend.domain.stock.exception.NotFoundStockException;
import com.juicycool.backend.domain.stock.presentation.dto.request.BuyReservRequestDto;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.domain.stock.service.impl.BuyReservationStockServiceImpl;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BuyReservationStockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserUtil userUtil;

    @InjectMocks
    private BuyReservationStockServiceImpl buyReservationStockService;

    private User mockUser;
    private Stock mockStock;
    private Reservation mockReservation;
    private BuyReservRequestDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockUser = User.builder()
                .points(100000L)
                .build();

        mockStock = Stock.builder()
                .code("1111")
                .build();

        dto = new BuyReservRequestDto(2L, 20000L);

        when(userUtil.getCurrentUser()).thenReturn(mockUser);
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.of(mockStock));
    }

    @Test
    @DisplayName("만약 주식 코드로 주식을 찾지 못한 경우")
    void If_not_found_stock_by_stock_code() {
        when(stockRepository.findByCode(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundStockException.class, () -> buyReservationStockService.execute("1111", dto));
    }

    @Test
    @DisplayName("만약 매수 예약 갯수가 1보다 작은 경우")
    void If_number_of_buy_reservation_less_than_one() {
        dto = new BuyReservRequestDto(0L, 20000L);

        assertThrows(InvalidBuyingNumberException.class, () -> buyReservationStockService.execute("1111", dto));
    }

    @Test
    @DisplayName("만약 총 매수 금액보다 내 포인트가 적은 경우")
    void If_my_point_less_than_total_purchase_amount() {
        dto = new BuyReservRequestDto(2L, 2000000L);

        assertThrows(InvalidBuyingNumberException.class, () -> buyReservationStockService.execute("1111", dto));
    }

    @Test
    @DisplayName("만약 정상적으로 매수 예약이 성공한 경우")
    void If_purchase_reservation_is_successful() {
        buyReservationStockService.execute("1111", dto);

        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }
}
