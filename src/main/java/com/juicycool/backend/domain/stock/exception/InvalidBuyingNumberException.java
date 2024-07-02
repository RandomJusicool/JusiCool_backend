package com.juicycool.backend.domain.stock.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class InvalidBuyingNumberException extends GlobalException {
    public InvalidBuyingNumberException() {
        super(ErrorCode.INVALID_BUYING_NUMBER);
    }
}
