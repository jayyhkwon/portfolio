package com.callbus.kyh.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PlayerType {
    @JsonProperty("0")
    CLIENT(0),
    @JsonProperty("1")
    BUS_DRIVER(1),
    @JsonProperty("2")
    BUS_COMPANY(2);

    private final int type;

    PlayerType(int type) {
        this.type = type;
    }
}
