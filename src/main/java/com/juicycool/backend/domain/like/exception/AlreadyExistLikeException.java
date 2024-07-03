package com.juicycool.backend.domain.like.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class AlreadyExistLikeException extends GlobalException {
    public AlreadyExistLikeException() {
        super(ErrorCode.ALREADY_EXIST_LIKE);
    }
}
