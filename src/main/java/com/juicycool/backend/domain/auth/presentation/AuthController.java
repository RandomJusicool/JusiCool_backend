package com.juicycool.backend.domain.auth.presentation;

import com.juicycool.backend.domain.auth.presentation.dto.request.SignInRequestDto;
import com.juicycool.backend.domain.auth.presentation.dto.request.SignUpRequestDto;
import com.juicycool.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.juicycool.backend.domain.auth.service.LogoutService;
import com.juicycool.backend.domain.auth.service.ReissueTokenService;
import com.juicycool.backend.domain.auth.service.SignInService;
import com.juicycool.backend.domain.auth.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final ReissueTokenService reissueTokenService;
    private final LogoutService logoutService;

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

    @PatchMapping()
    public ResponseEntity<TokenResponse> reissueToken(@RequestHeader("RefreshToken") String refreshToken) {
        TokenResponse response = reissueTokenService.execute(refreshToken);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout() {
        logoutService.execute();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
