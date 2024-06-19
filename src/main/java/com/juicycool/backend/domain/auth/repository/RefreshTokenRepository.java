package com.juicycool.backend.domain.auth.repository;

import com.juicycool.backend.domain.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String > {
    Optional<RefreshToken> findByToken(String refreshToken);
}
