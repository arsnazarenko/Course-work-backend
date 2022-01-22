package com.itmo.squid.repo;

import com.itmo.squid.domain.Death;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeathRepo extends JpaRepository<Death, Long> {
}
