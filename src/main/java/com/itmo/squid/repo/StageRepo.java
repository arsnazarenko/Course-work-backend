package com.itmo.squid.repo;

import com.itmo.squid.domain.Stage;
import com.itmo.squid.domain.StageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface StageRepo extends JpaRepository<Stage, Long> {
    Optional<Stage> findStageByStatusEquals(@NotNull StageStatus status);
}
