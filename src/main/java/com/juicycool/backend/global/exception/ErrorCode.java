package com.juicycool.backend.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // token
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),
    INVALID_TOKEN(401, "유효하지 않는 토큰입니다."),

    // auth
    DUPLICATE_EMAIL(409, "중복되는 이메일입니다."),

    // mail
    ALREADY_AUTHENTICATED_MAIL(400, "이미 인증된 메일입니다.");

    private final int status;
    private final String message;
}
