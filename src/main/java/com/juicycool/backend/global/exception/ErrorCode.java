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
    EXPIRED_REFRESH_TOKEN(401, "만료된 리프레쉬 토큰입니다."),
    INVALID_REFRESH_TOKEN(401, "변질된 리프레쉬 토큰입니다."),

    // auth
    DUPLICATE_EMAIL(409, "중복되는 이메일입니다."),

    // mail
    ALREADY_AUTHENTICATED_MAIL(400, "이미 인증된 메일입니다."),
    EXPIRED_AUTH_CODE(401, "이미 만료된 인증코드입니다."),
    NOT_VERIFICATION_MAIL(401, "인증된 메일이 아닙니다."),

    // user
    NOT_FOUND_USER(404, "해당 유저를 찾을 수 없습니다."),
    NOT_MATCH_PASSWORD(400, "비밀번호가 일치하지 않습니다.");

    private final int status;
    private final String message;
}
