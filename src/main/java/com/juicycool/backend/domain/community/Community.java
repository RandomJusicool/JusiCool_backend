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
public class Community extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long board_number;

    private String title;

    private String content;

    private Long thumbs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
