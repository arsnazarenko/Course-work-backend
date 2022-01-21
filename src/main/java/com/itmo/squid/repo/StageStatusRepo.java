package com.itmo.squid.repo;

import com.itmo.squid.domain.StageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageStatusRepo extends JpaRepository<StageStatus, Long> {
}
