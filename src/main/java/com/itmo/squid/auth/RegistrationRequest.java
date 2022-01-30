package com.itmo.squid.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class RegistrationRequest {
    @Size(min = 4)
    @NotBlank
    private String login;
    @Size(min = 4)
    @NotBlank
    private String password;
}
