package com.juicycool.backend.domain.stock.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotFoundOwnedStockException extends GlobalException {
    public NotFoundOwnedStockException() {
        super(ErrorCode.NOT_FOUND_OWNED_STOCK);
    }
}
