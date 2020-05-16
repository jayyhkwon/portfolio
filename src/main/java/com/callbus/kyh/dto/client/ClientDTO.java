package com.callbus.kyh.dto.client;

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
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
}
