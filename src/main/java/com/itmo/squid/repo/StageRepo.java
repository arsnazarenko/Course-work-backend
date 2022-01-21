package com.itmo.squid.repo;

import com.itmo.squid.domain.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepo extends JpaRepository<Stage, Long> {
}
