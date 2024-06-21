package com.juicycool.backend.domain.board.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotMatchUserException extends GlobalException {
    public NotMatchUserException() {
        super(ErrorCode.NOT_MATCH_USER);
    }
}
