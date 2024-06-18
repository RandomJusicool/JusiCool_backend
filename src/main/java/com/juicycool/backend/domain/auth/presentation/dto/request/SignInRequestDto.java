package com.juicycool.backend.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
