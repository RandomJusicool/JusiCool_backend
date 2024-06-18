package com.juicycool.backend.domain.auth.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotVerificationMailException extends GlobalException {
    public NotVerificationMailException() {
        super(ErrorCode.NOT_VERIFICATION_MAIL);
    }
}
