package com.juicycool.backend.domain.stock.repository;

import com.juicycool.backend.domain.stock.OwnedStocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedStocksRepository extends JpaRepository<OwnedStocks, Long> {
}
