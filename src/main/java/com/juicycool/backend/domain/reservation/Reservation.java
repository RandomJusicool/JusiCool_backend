package com.juicycool.backend.domain.reservation;


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

    private String stock_name;

    private Integer stock_code; // 주식 코드

    private Integer reservation_price; // 예약한 금액

    private Integer stock_num; // 예약한 주식 갯수

    private Integer stock_price; // 예약해놓은 주식 금액

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
