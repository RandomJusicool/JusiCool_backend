package com.juicycool.backend.domain.reservation.repository;

import com.juicycool.backend.domain.reservation.Reservation;
import com.juicycool.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
}
