package com.juicycool.backend.domain.receipt;

import com.juicycool.backend.domain.reservation.Status;
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
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockName;

    private Status status;

    private Long price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
