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

    private String email;

    private String name;

    private String password;

    private Long points;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void deductPoints(Long stockPoint) {
        this.points -= stockPoint;
    }
    public void addSellPoints(Long sellPoint) {this.points += sellPoint;}
}
