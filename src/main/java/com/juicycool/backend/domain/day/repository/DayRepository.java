package com.juicycool.backend.domain.day.repository;

import com.juicycool.backend.domain.day.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {
}
