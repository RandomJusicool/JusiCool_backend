package com.juicycool.backend.domain.reservation.repository;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.reservation.Status;
import com.juicycool.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    Boolean existsByUserAndStockCode(User user, String stockCode);
    Optional<Reservation> findByUserAndStockCodeAndStatus(User user, String stockCode, Status status);
}
