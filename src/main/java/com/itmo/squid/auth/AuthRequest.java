package com.itmo.squid.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
