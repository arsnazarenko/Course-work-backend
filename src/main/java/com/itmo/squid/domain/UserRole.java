package com.itmo.squid.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String toString() {
        return this.name();
    }

    @JsonCreator
    public static TeamType fromText(String text) {
        return TeamType.valueOf(text);
    }

}