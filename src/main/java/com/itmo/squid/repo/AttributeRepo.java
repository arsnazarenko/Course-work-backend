package com.itmo.squid.repo;

import com.itmo.squid.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {

}
