package com.itmo.squid.repo;

import com.itmo.squid.domain.Contraband;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContrabandRepo extends JpaRepository<Contraband, Long> {
}
