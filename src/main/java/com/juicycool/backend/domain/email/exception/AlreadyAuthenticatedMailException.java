package com.juicycool.backend.domain.email.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class AlreadyAuthenticatedMailException extends GlobalException {
    public AlreadyAuthenticatedMailException() {
        super(ErrorCode.ALREADY_AUTHENTICATED_MAIL);
    }
}
