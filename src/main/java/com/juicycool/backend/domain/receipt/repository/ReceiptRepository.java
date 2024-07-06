package com.juicycool.backend.domain.receipt.repository;

import com.juicycool.backend.domain.receipt.Receipt;
import com.juicycool.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByUser(User user);
}
