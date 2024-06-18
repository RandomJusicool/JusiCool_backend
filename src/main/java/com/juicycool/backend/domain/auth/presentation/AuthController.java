package com.juicycool.backend.domain.auth.presentation;

import com.juicycool.backend.domain.auth.presentation.dto.request.SignInRequestDto;
import com.juicycool.backend.domain.auth.presentation.dto.request.SignUpRequestDto;
import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.juicycool.backend.domain.auth.service.SignInService;
import com.juicycool.backend.domain.auth.service.SignUpService;
import jakarta.validation.Valid;
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
    private final SignInService signInService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequestDto dto) {
        signUpService.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@Valid @RequestBody SignInRequestDto dto) {
        TokenResponse response = signInService.execute(dto);
        return ResponseEntity.ok(response);
    }


}
