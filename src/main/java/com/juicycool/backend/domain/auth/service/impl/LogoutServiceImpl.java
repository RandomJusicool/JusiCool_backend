package com.juicycool.backend.domain.auth.service.impl;

import com.juicycool.backend.domain.auth.repository.RefreshTokenRepository;
import com.juicycool.backend.domain.auth.service.LogoutService;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserUtil userUtil;

    public void execute() {
        User user = userUtil.getCurrentUser();
        refreshTokenRepository.deleteById(user.getEmail());
    }
}
