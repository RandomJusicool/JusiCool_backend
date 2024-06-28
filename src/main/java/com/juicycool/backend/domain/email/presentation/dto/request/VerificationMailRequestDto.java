package com.juicycool.backend.domain.email.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VerificationMailRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String authCode;
}
