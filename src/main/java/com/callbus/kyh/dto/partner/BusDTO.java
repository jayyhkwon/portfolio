package com.callbus.kyh.dto.partner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BusDTO {

    @JsonIgnore
    private long id;
    @JsonIgnore
    private long busTypeId;
    private String name;
    private int capacity;
    private int busYear;
    @JsonIgnore
    private LocalDateTime registerDate;
    @JsonIgnore
    private LocalDateTime updateDate;
}
