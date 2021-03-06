package com.callbus.kyh.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Alias("ClientDTO")
public class ClientDTO {

    private long id;
    private String phoneNumber;
    private boolean pushAgree;
    @JsonIgnore
    private LocalDateTime registerDate;
    @JsonIgnore
    private LocalDateTime updateDate;
}
