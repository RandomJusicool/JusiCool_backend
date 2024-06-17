package com.juicycool.backend.domain.email.presentation;

import com.juicycool.backend.domain.email.presentation.dto.request.SendMailRequestDto;
import com.juicycool.backend.domain.email.service.SendMailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class MailController {

    private final SendMailService sendMailService;

    @PostMapping
    public ResponseEntity<Void> sedMail(@Valid @RequestBody SendMailRequestDto dto) {
        sendMailService.execute(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
