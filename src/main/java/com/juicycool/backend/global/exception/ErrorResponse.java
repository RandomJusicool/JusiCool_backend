package com.juicycool.backend.global.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(int status, String message) {}
