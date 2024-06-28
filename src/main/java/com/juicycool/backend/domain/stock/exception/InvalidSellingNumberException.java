package com.juicycool.backend.domain.stock.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class InvalidSellingNumberException extends GlobalException {
    public InvalidSellingNumberException() {
        super(ErrorCode.INVALID_SELLING_NUMBER);
    }
}
