package com.juicycool.backend.domain.board.exception;


import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotFoundBoardException extends GlobalException {
    public NotFoundBoardException() {
        super(ErrorCode.NOT_FOUND_BOARD);
    }
}
