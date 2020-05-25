package com.callbus.kyh.mapper;

import com.callbus.kyh.dto.ticket.BiddingDetailDTO;
import com.callbus.kyh.dto.ticket.TicketDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketMapper {

    Long createTicket(TicketDTO ticketDTO);

    int updateTicket(TicketDTO ticket);

    List<TicketDTO> getTickets(long clientId);

    List<BiddingDetailDTO> getBiddingsDetail(long ticketId);

    boolean hasTicket(long clientId, long ticketId);

    int cancelTicket(long clientId, long ticketId, TicketDTO.Status status, TicketDTO.CancelReason cancelReason);
}
