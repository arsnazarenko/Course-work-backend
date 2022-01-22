package com.itmo.squid.repo;

import com.itmo.squid.domain.TeamOnStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamOnStageRepo extends JpaRepository<TeamOnStage, Long> {
}
