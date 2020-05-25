package com.callbus.kyh.dto.ticket;

import com.callbus.kyh.dto.client.ClientDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Ticket 엔티티와 Bidding 엔티티의 1:n 관계를 나타내고, view로 반환하기 위한 DTO
 * @author yhkwon
 */

@Getter
@Setter
public class TicketBiddingDTO {

    private TicketDTO ticket;

    private ClientDTO client;

    private List<BiddingDetailDTO> biddings = new ArrayList<>();

}
