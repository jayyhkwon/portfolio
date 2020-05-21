package com.callbus.kyh.dto.ticket;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TicketDTO {

    private long id;
    @JsonIgnore
    private long clientId;

    private String srcName;
    private String srcAddress;
    private double srcLatitude;
    private double srcLongitude;

    private String dstName;
    private String dstAddress;
    private double dstLatitude;
    private double dstLongitude;

    private long tripTypeId;
    private LocalDateTime departDate;
    private LocalDateTime returnDate;
    private int together;
    private int userCnt;

    private int card;
    private long busTypeId;
    private int taxReceipt;
    private String stopover;
    private double distance;

    private Status status = Status.DEFAULT;
    private double suggestPrice;
    private String comment;

    private LocalDateTime registerDate;
    private LocalDateTime expireDate;
    private LocalDateTime updateDate;

    // 예약대기, 입금완료, 운행중, 취소, 종료
    public enum Status {
        DEFAULT, PAY_COMPLETED, ON_WAY, CANCELED, CLOSED
    }

    // 취소(기타), 취소(대중교통 이용), 취소(다른 전세버스 이용)
    public enum CancelReason {
        @JsonProperty("0") CANCELED_ETC,
        @JsonProperty("1") CANCELED_PT,
        @JsonProperty("2") CANCELED_ANO,
    }

    // 결혼, 워크샵, MT/OT, 단체관광, 모임
    // 종교행사, 현장학습, 동호회, 콘서트, 기타
    public enum TripType {
        @JsonProperty("0") WEDDING(0),
        @JsonProperty("1") WORKSHOP(1),
        @JsonProperty("2") MT(2),
        @JsonProperty("3") TOUR(3),
        @JsonProperty("4") MEETING(4),
        @JsonProperty("5") RELIGION(5),
        @JsonProperty("6") FIELD_TRIP(6),
        @JsonProperty("7") CLUB(7),
        @JsonProperty("8") CONCERT(8),
        @JsonProperty("9") ETC(9);

        private final int type;

        TripType(int type) {
            this.type = type;
        }
    }

}
