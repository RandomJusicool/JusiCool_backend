package com.juicycool.backend.domain.auth.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequestDto {

    private String email;
    private String name;
    private String password;
}
