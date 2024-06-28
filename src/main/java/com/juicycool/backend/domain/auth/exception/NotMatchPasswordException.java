package com.juicycool.backend.domain.auth.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotMatchPasswordException extends GlobalException {
    public NotMatchPasswordException() {
        super(ErrorCode.NOT_MATCH_PASSWORD);
    }
}
