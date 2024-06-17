package com.juicycool.backend.domain.email.presentation;

import com.juicycool.backend.domain.email.presentation.dto.request.SendMailRequestDto;
import com.juicycool.backend.domain.email.presentation.dto.request.VerificationMailRequestDto;
import com.juicycool.backend.domain.email.service.SendMailService;
import com.juicycool.backend.domain.email.service.VerificationMailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class MailController {

    private final SendMailService sendMailService;
    private final VerificationMailService verificationMailService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@Valid @RequestBody SendMailRequestDto dto) {
        sendMailService.execute(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<Void> verificationMail(@RequestParam String email, @RequestParam String authCode) {
        verificationMailService.execute(email, authCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
