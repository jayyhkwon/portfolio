package com.callbus.kyh.dto.partner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BusTypeDTO {

    public enum BusType {
        MINI, MINI_PRIMIUM, MEDIUM, MEDIUM_PRIMUIM,
        LARGE, LARGE_PRIMIUM, PRIMIUM
    }

    private long id;
    private BusType busType;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
}
