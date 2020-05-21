package com.callbus.kyh.dto.partner;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BusCompanyDTO {

    private long id;
    private String companyName;
    private String garageName;
    private String garageAddress;
    private String businessLicense;
    private int driverCount;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

}
