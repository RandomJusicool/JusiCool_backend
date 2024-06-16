package com.juicycool.backend.domain.auth.presentation;

import com.juicycool.backend.domain.auth.presentation.dto.request.SignUpRequestDto;
import com.juicycool.backend.domain.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDto dto) {
        signUpService.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
