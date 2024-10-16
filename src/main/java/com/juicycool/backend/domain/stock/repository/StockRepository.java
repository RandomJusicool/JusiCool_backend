package com.juicycool.backend.domain.stock.repository;

import com.juicycool.backend.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByCode(String code);
}
