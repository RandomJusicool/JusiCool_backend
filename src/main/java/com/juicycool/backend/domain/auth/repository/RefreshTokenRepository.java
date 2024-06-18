package com.juicycool.backend.domain.auth.repository;

import com.juicycool.backend.domain.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
