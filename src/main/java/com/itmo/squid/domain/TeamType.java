package com.itmo.squid.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TeamType {
    PAIR, TEAM, SINGLE;

    @Override
    public String toString() {
        return this.name();
    }

    @JsonCreator
    public static TeamType fromText(String text) {
        return TeamType.valueOf(text);
    }

}