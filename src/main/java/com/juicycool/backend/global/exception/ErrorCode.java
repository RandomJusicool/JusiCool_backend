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

    // stock
    NOT_FOUND_STOCK(404, "해당 주식을 찾을 수 없습니다"),
    POINT_LOWER_THAN_PRESENT_PRICE(400, "매수 가격보다 포인트가 더 적습니다"),

    // mail
    ALREADY_AUTHENTICATED_MAIL(400, "이미 인증된 메일입니다."),
    EXPIRED_AUTH_CODE(401, "이미 만료된 인증코드입니다."),
    NOT_VERIFICATION_MAIL(401, "인증된 메일이 아닙니다."),

    // community
    NOT_FOUND_COMMUNITY(404, "존재하지 않는 커뮤니티입니다."),

    // board
    NOT_FOUND_BOARD(404, "해당 게시판을 찾을 수 없습니다."),

    // like
    NOT_FOUND_LIKE(404, "유저가 누른 좋아요를 찾을 수 없습니다."),

    // user
    NOT_FOUND_USER(404, "해당 유저를 찾을 수 없습니다."),
    NOT_MATCH_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    NOT_MATCH_USER(400, "등록된 유저와 일치하지 않습니다.");

    private final int status;
    private final String message;
}
