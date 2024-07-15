package com.juicycool.backend.domain.reservation;


import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reservationPrice; // 예약한 금액

    private Long stockNum; // 예약한 주식 갯수

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Column(columnDefinition = "TEXT")
    private Stock stock;

    public void plusStockNum(Long num) {
        this.stockNum += num;
    }
}
