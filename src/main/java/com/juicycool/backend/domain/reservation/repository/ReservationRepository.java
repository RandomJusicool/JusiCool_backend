package com.juicycool.backend.domain.reservation.repository;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    Optional<Reservation> findByUserAndStockCode(User user, Integer stockCode);
    Boolean existsByUserAndStockCode(User user, Integer stockCode);
}
