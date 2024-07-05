package com.juicycool.backend.domain.email.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class FailSendMailException extends GlobalException {
    public FailSendMailException() {
        super(ErrorCode.FAIL_SEND_MAIL);
    }
}
