package com.itmo.squid.repo;

import com.itmo.squid.domain.Human;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanRepo extends JpaRepository<Human, Long> {
}
