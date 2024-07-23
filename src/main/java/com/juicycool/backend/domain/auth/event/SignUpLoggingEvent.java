package com.juicycool.backend.domain.auth.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpLoggingEvent {
    private String email;
    private String name;
}
