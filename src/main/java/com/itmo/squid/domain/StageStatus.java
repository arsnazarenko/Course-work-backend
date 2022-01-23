package com.itmo.squid.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StageStatus {
        CONTINUOUS, END, NOT_OPEN;


        @Override
        public String toString() {
                return this.name();
        }

        @JsonCreator
        public static StageStatus fromText(String s) {
                return StageStatus.fromText(s);
        }
}
