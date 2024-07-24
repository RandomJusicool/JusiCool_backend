package com.juicycool.backend.global.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.juicycool.backend.global.exception.ErrorCode;
import com.juicycool.backend.global.exception.ErrorResponse;
import com.juicycool.backend.global.exception.GlobalException;
import com.juicycool.backend.global.filter.event.ErrorLoggingEvent;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response);
        } catch (GlobalException e) {
            sendError(response, e.getErrorCode());
        } catch (Exception e) {
            sendError(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendError(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
        String responseString = objectMapper.writeValueAsString(errorResponse);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(responseString);

        applicationEventPublisher.publishEvent(new ErrorLoggingEvent(response, errorCode));
    }
}
