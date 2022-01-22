package com.itmo.squid.repo;

import antlr.debug.ParserTokenAdapter;
import com.itmo.squid.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepo extends JpaRepository<Participant, Long> {
}
