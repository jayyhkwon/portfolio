package com.callbus.kyh.service;

import com.callbus.kyh.dto.client.ClientDTO;
import com.callbus.kyh.dto.ticket.BiddingDTO;
import com.callbus.kyh.dto.ticket.BiddingDetailDTO;
import com.callbus.kyh.dto.ticket.TicketBiddingDTO;
import com.callbus.kyh.dto.ticket.TicketDTO;
import com.callbus.kyh.error.InvalidRequestException;
import com.callbus.kyh.error.UnknownException;
import com.callbus.kyh.mapper.ClientMapper;
import com.callbus.kyh.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Mybatis의 경우
 * insert, update문 실행 시 적용된 row 수가 return 된다.
 */

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final ClientMapper clientMapper;

    @Autowired
    private final TicketMapper ticketMapper;


    // select문의 경우 결과가 없는 경우 null이 리턴된다. (primitive type)
    public long getClientIdByPhoneNumber(String phoneNumber) {
        Long clientId;
        clientId = clientMapper.findIdByPhoneNumber(phoneNumber);
        if (clientId == null)
            return 0L;
        return clientId;
    }


    public Long createTicket(TicketDTO ticket) {
        ticketMapper.createTicket(ticket);
        long id = ticket.getId();
        if (id == 0)
            throw new UnknownException("티켓 생성에 실패하였습니다");
        return id;
    }

    public void updateTicket(TicketDTO ticket) {
        int result = ticketMapper.updateTicket(ticket);
        if (result != 1) {
            throw new UnknownException("티켓 업데이트에 실패하였습니다");
        }
    }

    public void cancelTicket(long clientId, long ticketId, TicketDTO.CancelReason cancelReason) {
        boolean hasTicket = ticketMapper.hasTicket(clientId, ticketId);

        if (!hasTicket)
            throw new InvalidRequestException("티켓이 존재하지 않습니다");

        int result = ticketMapper.cancelTicket(clientId, ticketId, TicketDTO.Status.CANCELED, cancelReason);

        if (result != 1)
            throw new UnknownException("티켓 삭제에 문제가 발생하였습니다");

    }


    public List<TicketBiddingDTO> getTicketBiddings(long clientId) {

        List<TicketBiddingDTO> ticketBiddings = new ArrayList<>();

        List<TicketDTO> ticketDTOs = ticketMapper.getTickets(clientId);

        ClientDTO client = clientMapper.findById(clientId);

        if (!ticketDTOs.isEmpty()) {
            for (TicketDTO ticket : ticketDTOs) {
                TicketBiddingDTO ticketBidding = new TicketBiddingDTO();

                long ticketId = ticket.getId();
                List<BiddingDetailDTO> biddings = ticketMapper.getBiddingsDetail(ticketId);

                ticketBidding.setClient(client);
                ticketBidding.setTicket(ticket);
                ticketBidding.setBiddings(biddings);

                ticketBiddings.add(ticketBidding);
            }
        }

        return ticketBiddings;
    }


}
