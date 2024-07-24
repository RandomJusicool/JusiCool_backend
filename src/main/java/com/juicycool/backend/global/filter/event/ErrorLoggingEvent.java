package com.juicycool.backend.global.filter.event;

import com.juicycool.backend.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorLoggingEvent {
    private HttpServletResponse response;
    private ErrorCode errorCode;
}
