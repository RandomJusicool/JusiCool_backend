package com.juicycool.backend.domain.stock.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotFoundStockException extends GlobalException {
    public NotFoundStockException() {
        super(ErrorCode.NOT_FOUND_STOCK);
    }
}
