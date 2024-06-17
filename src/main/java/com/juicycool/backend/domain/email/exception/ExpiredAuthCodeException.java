package com.juicycool.backend.domain.email.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class ExpiredAuthCodeException extends GlobalException {
    public ExpiredAuthCodeException() {
        super(ErrorCode.EXPIRED_AUTH_CODE);
    }
}
