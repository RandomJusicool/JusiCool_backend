package com.juicycool.backend.domain.like.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotFoundLikeException extends GlobalException {
    public NotFoundLikeException() {
        super(ErrorCode.NOT_FOUND_LIKE);
    }
}
