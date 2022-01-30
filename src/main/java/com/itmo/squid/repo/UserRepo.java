package com.itmo.squid.repo;


import com.itmo.squid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String name);
}
