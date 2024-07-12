package com.juicycool.backend.domain.user.service.impl;

import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.repository.OwnedStocksRepository;
import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.converter.UserConverter;
import com.juicycool.backend.domain.user.presentation.dto.response.GetMyOwnedStockResponseDto;
import com.juicycool.backend.domain.user.service.GetMyOwnedStockService;
import com.juicycool.backend.domain.user.util.UserUtil;
import com.juicycool.backend.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetMyOwnedStockServiceImpl implements GetMyOwnedStockService {

    private final OwnedStocksRepository ownedStocksRepository;
    private final UserUtil userUtil;
    private final UserConverter userConverter;


    public List<GetMyOwnedStockResponseDto> execute() {
        User user = userUtil.getCurrentUser();

        List<OwnedStocks> ownedStocks = ownedStocksRepository.findByUser(user);

        return ownedStocks.stream()
                .map(userConverter::toDto)
                .collect(Collectors.toList());
    }
}
