package com.itmo.squid.repo;

import com.itmo.squid.domain.ParticipantRequest;
import com.itmo.squid.domain.ReqType;
import com.itmo.squid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantReqRepo extends JpaRepository<ParticipantRequest, Long> {
    Optional<ParticipantRequest> findParticipantRequestByUser(User user);
    List<ParticipantRequest> findAllByTypeEquals(ReqType type);
}
