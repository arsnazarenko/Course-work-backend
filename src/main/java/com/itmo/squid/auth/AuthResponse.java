package com.itmo.squid.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itmo.squid.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    private String token;
    private UserRole role;
    private Long userId;
}
