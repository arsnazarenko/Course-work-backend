package com.itmo.squid.repo;

import com.itmo.squid.domain.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepo extends JpaRepository<Bet, Long> {
}
