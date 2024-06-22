package com.juicycool.backend.domain.community.presentation;

import com.juicycool.backend.domain.community.presentation.dto.response.GetCommunityListResponseDto;
import com.juicycool.backend.domain.community.service.GetCommunityService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community")
public class CommunityController {

    private final GetCommunityService getCommunityService;

    @GetMapping
    public ResponseEntity<List<GetCommunityListResponseDto>> getCommunity() {
        List<GetCommunityListResponseDto> response = getCommunityService.execute();
        return ResponseEntity.ok(response);
    }
}
