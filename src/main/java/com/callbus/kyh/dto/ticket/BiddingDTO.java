package com.callbus.kyh.dto.ticket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BiddingDTO {

    private long id;
    private long ticketId;
    private long driverId;
    private double price;
    private double additionalFee1;
    private double additionalFee2;
    private double additionalFee3;
    private double additionalFee4;
    private double additionalFee5;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

}
