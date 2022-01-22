package com.itmo.squid.repo;

import com.itmo.squid.domain.MarketGood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketGoodRepo extends JpaRepository<MarketGood, Long> {
}
