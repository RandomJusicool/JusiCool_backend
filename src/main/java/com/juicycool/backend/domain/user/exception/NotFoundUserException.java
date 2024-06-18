package com.juicycool.backend.domain.user.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotFoundUserException extends GlobalException {
    public NotFoundUserException() {
        super(ErrorCode.NOT_FOUND_USER);
    }
}
