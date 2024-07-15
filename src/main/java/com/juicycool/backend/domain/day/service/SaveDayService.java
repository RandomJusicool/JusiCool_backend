package com.juicycool.backend.domain.day.service;

import com.juicycool.backend.domain.day.Day;
import com.juicycool.backend.domain.day.repository.DayRepository;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@TransactionService
@RequiredArgsConstructor
public class SaveDayService {

    private final DayRepository dayRepository;
    private final StockRepository stockRepository;

    public void saveDay() {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock: stocks) {
            Day day = Day.builder()
                    .code(stock.getCode())
                    .marketPrice(stock.getMarketPrice())
                    .highPrice(stock.getHighPrice())
                    .presentPrice(stock.getPresentPrice())
                    .lowPrice(stock.getLowPrice())
                    .volume(stock.getTransactionVolume())
                    .upDownPercent((Math.floor(((double)(stock.getPresentPrice() - stock.getMarketPrice()) / stock.getMarketPrice()) * 100 * 10) / 10.0))
                    .storeAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a HH:mm")))
                    .build();

            dayRepository.save(day);
        }
    }
}
