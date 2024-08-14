package com.juicycool.backend.domain.user;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long points;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

    public void deductPoints(Long stockPoint) {
        this.points -= stockPoint;
    }
    public void addSellPoints(Long sellPoint) {this.points += sellPoint;}
}
