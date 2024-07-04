package com.juicycool.backend.domain.day.service;

import com.juicycool.backend.domain.day.Day;
import com.juicycool.backend.domain.day.repository.DayRepository;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.stock.repository.StockRepository;
import com.juicycool.backend.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@TransactionService
@RequiredArgsConstructor
public class SaveDayService {

    private final DayRepository dayRepository;
    private final StockRepository stockRepository;

    public void saveDay() {
        List<Stock> stocks = stockRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
        String format = simpleDateFormat.format(now);

        for (Stock stock: stocks) {
            Day day = Day.builder()
                    .code(stock.getCode())
                    .marketPrice(stock.getMarketPrice())
                    .highPrice(stock.getHighPrice())
                    .headPrice(stock.getHeadPrice())
                    .presentPrice(stock.getPresentPrice())
                    .lowPrice(stock.getLowPrice())
                    .volume(stock.getTransactionVolume())
                    .upDownPercent(stock.getFluctuationComparedPreviousDay())
                    .storeAt(format)
                    .build();

            dayRepository.save(day);
        }
    }
}
