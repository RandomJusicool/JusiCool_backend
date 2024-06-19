package com.juicycool.backend.global.security.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class ExpiredTokenException extends GlobalException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
