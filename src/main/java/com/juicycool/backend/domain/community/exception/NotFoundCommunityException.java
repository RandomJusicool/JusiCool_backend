package com.juicycool.backend.domain.community.exception;

import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.GlobalException;

public class NotFoundCommunityException extends GlobalException {

    public NotFoundCommunityException() {
        super(ErrorCode.NOT_FOUND_COMMUNITY);
    }
}
