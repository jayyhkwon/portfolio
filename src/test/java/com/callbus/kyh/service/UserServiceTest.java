package com.callbus.kyh.service;

import com.callbus.kyh.dto.client.ClientDTO;
import com.callbus.kyh.dto.ticket.BiddingDetailDTO;
import com.callbus.kyh.dto.ticket.TicketDTO;
import com.callbus.kyh.error.InvalidRequestException;
import com.callbus.kyh.error.UnknownException;
import com.callbus.kyh.mapper.ClientMapper;
import com.callbus.kyh.mapper.TicketMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by yhkwon on 2020/05/20
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    ClientMapper clientMapper;
    @Mock
    TicketMapper ticketMapper;
    @Mock
    TicketDTO ticket;
    @Mock
    ClientDTO clientDTO;
    @Mock
    List<TicketDTO> ticketDTOs;
    @Mock
    List<BiddingDetailDTO> biddings;
    @Mock
    Iterator<TicketDTO> iterator;

    @Before
    public void setUp() {
        userService = new UserService(clientMapper, ticketMapper);
    }

    @Test
    public void getClientId_고객테이블_PK가져오기() {
        userService.getClientIdByPhoneNumber(anyString());
    }

    @Test
    public void createTicket_티켓생성_정상() {
        given(ticket.getId()).willReturn(1L);

        userService.createTicket(ticket);
    }

    @Test(expected = UnknownException.class)
    public void createTicket_티켓생성_실패() {
        given(ticket.getId()).willReturn(0L);

        userService.createTicket(ticket);
    }

    @Test
    public void updateTicket_티켓업데이트_정상() {
        given(ticketMapper.updateTicket(ticket)).willReturn(1);

        userService.updateTicket(ticket);

    }

    @Test(expected = UnknownException.class)
    public void updateTicket_티켓업데이트_실패() {
        given(ticketMapper.updateTicket(ticket)).willReturn(0);

        userService.updateTicket(ticket);

    }

    @Test
    public void cancelTicket_티켓삭제() {
        given(ticketMapper.hasTicket(anyLong(),anyLong())).willReturn(true);
        given(ticketMapper.cancelTicket(anyLong(),anyLong(),any(TicketDTO.Status.class),any(TicketDTO.CancelReason.class))).willReturn(1);

        userService.cancelTicket(1L,1L, TicketDTO.CancelReason.CANCELED_ETC);
    }

    @Test(expected = InvalidRequestException.class)
    public void cancelTicket_티켓삭제_티켓이_없는_경우() {
        given(ticketMapper.hasTicket(anyLong(),anyLong())).willReturn(false);
        given(ticketMapper.cancelTicket(anyLong(),anyLong(),any(TicketDTO.Status.class),any(TicketDTO.CancelReason.class))).willReturn(1);

        userService.cancelTicket(1L,1L, TicketDTO.CancelReason.CANCELED_ETC);
    }

    @Test(expected = UnknownException.class)
    public void cancelTicket_티켓삭제_업데이트_쿼리에_문제가_발생한_경우() {
        given(ticketMapper.hasTicket(anyLong(),anyLong())).willReturn(true);
        given(ticketMapper.cancelTicket(anyLong(),anyLong(),any(TicketDTO.Status.class),any(TicketDTO.CancelReason.class))).willReturn(0);

        userService.cancelTicket(1L,1L, TicketDTO.CancelReason.CANCELED_ETC);
    }

    @Test
    public void getTicketBiddings_티켓이_존재하는_경우_성공() {

        given(ticketMapper.getTickets(anyLong())).willReturn(ticketDTOs);
        given(clientMapper.findById(anyLong())).willReturn(clientDTO);
        given(ticketMapper.getBiddingsDetail(anyLong())).willReturn(biddings);

        given(ticketDTOs.isEmpty()).willReturn(false); // 티켓이 존재하는 경우

        given(ticketDTOs.iterator()).willReturn(iterator); // for문을 위한 iterator mocking
        given(iterator.hasNext()).willReturn(true).willReturn(false);
        given(iterator.next()).willReturn(ticket);

        given(ticket.getId()).willReturn(anyLong());

        userService.getTicketBiddings(1L);
    }


}