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

    @Column(nullable = false)
    private Long reservationPrice; // 예약한 금액

    @Column(nullable = false)
    private Long stockNum; // 예약한 주식 갯수

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String stockCode;

    public void plusStockNum(Long num) {
        this.stockNum += num;
    }
}
