package com.itmo.squid.repo;

import com.itmo.squid.domain.Vip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VipRepo extends JpaRepository<Vip, Long> {
}
