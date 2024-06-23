package com.juicycool.backend.domain.stock.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class PointLowerThanPresentPriceException extends GlobalException {

    public PointLowerThanPresentPriceException() {
        super(ErrorCode.POINT_LOWER_THAN_PRESENT_PRICE);
    }
}
