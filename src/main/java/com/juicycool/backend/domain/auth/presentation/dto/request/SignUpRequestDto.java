package com.juicycool.backend.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequestDto {
    @Pattern(regexp = ".+@.+", message = "이메일 형식에 맞지 않습니다.")
    private String email;
    private String name;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])|(?=.*[A-Za-z])(?=.*[^A-Za-z0-9])|(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;
}
