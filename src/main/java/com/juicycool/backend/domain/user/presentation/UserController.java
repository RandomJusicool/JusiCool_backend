package com.juicycool.backend.domain.user.presentation;

import com.juicycool.backend.domain.user.presentation.dto.response.GetMyBoardResponseDto;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyOwnedStockResponseDto;
import com.juicycool.backend.domain.user.service.GetMyBoardService;
import com.juicycool.backend.domain.user.service.GetMyOwnedStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final GetMyOwnedStockService getMyOwnedStockService;
    private final GetMyBoardService getMyBoardService;

    @GetMapping
    public ResponseEntity<List<GetMyOwnedStockResponseDto>> getMyOwnedStock() {
        List<GetMyOwnedStockResponseDto> response = getMyOwnedStockService.execute();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/board")
    public ResponseEntity<List<GetMyBoardResponseDto>> getMyBoard() {
        List<GetMyBoardResponseDto> response = getMyBoardService.execute();
        return ResponseEntity.ok(response);
    }

}
