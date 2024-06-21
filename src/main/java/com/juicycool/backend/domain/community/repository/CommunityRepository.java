package com.juicycool.backend.domain.community.repository;

import com.juicycool.backend.domain.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    boolean existsById(Long id);
}
