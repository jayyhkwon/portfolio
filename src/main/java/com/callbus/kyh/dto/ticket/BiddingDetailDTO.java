package com.callbus.kyh.dto.ticket;

import com.callbus.kyh.dto.partner.BusCompanyDTO;
import com.callbus.kyh.dto.partner.BusDTO;
import com.callbus.kyh.dto.partner.BusDriverDTO;
import com.callbus.kyh.dto.partner.BusTypeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
/**
 * Bidding 엔티티를 view로 반환하기 위한 DTO
 * @author yhkwon
 */
public class BiddingDetailDTO {

    private long id;
    private long ticketId;
    @JsonIgnore
    private long driverId;
    @JsonProperty("partner")
    private BusDriverDTO busDriverDTO;
    @JsonProperty("bus")
    private BusDTO busDTO;
    private BusTypeDTO.BusType busType;
    @JsonProperty("busCompany")
    private BusCompanyDTO busCompanyDTO;
    private double price;
    private double additionalFee1;
    private double additionalFee2;
    private double additionalFee3;
    private double additionalFee4;
    private double additionalFee5;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

}
