package com.callbus.kyh.dto.partner;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BusDriverDTO {

    // 운행 가능, 운행 불가, 휴면 상태
    public enum Status {
        ABLE, DISABLE, DORMANT
    }

    private long id;
    @JsonIgnore
    private long busId;
    @JsonIgnore
    private long companyId;
    private String faceImg;
    private String name;
    private String phoneNumber;
    private Status status;
    private String carPhoto1;
    private String carPhoto2;
    private String carPhoto3;
    private String carPhoto4;
    private String comment;
    private double score;
    private boolean insurance;
    private int biddingCnt;
    private int concurrentBiddingCnt;
    private int reviewCnt;
    private String carNum;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
}
