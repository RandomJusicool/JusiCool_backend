package com.juicycool.backend.domain.email.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendMailRequestDto {
    @NotBlank
    private String email;
}
