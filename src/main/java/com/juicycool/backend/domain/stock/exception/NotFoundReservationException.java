package com.juicycool.backend.domain.stock.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotFoundReservationException extends GlobalException {
    public NotFoundReservationException() {
        super(ErrorCode.NOT_FOUND_RESERVATION);
    }
}
