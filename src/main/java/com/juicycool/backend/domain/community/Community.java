package com.juicycool.backend.domain.community;

import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.global.entity.BaseEntity;
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
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer boardNum;

    public void plusBoardNum() {boardNum += 1;}
    public void minusBoardNum() {boardNum -= 1;}
}
