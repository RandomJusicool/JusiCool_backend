package com.juicycool.backend.domain.stock;

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
public class OwnedStocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockNumber; // 보유 중인 갯수

    private Long points;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @Column(columnDefinition = "TEXT")
    private Stock stock;

    public void discountStockNumber(Long num) {
        stockNumber -= num;
    }
    public void plusStockNum(Long num) {stockNumber += num;}
    public void plusPoints(Long allPoint) {points += allPoint;}
    public void minusPoints(Long discountPoint) {points -= discountPoint;}

}
