package com.juicycool.backend.domain.stock.repository;

import com.juicycool.backend.domain.stock.OwnedStocks;
import com.juicycool.backend.domain.stock.Stock;
import com.juicycool.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnedStocksRepository extends JpaRepository<OwnedStocks, Long> {
    Optional<OwnedStocks> findByUserAndStock(User user, Stock stock);
}
