package com.callbus.kyh.controller;

import com.callbus.kyh.aop.LoginCheck;
import com.callbus.kyh.dto.ticket.TicketBiddingDTO;
import com.callbus.kyh.dto.ticket.TicketDTO;
import com.callbus.kyh.service.UserService;
import com.callbus.kyh.utils.SessionUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

// TODO : Reqeust argument 검증하기
public class UserController {

    private final UserService userService;

    /**
     * 티켓 생성
     *
     * @param request
     * @param session
     * @return
     */
    @LoginCheck
    @PostMapping("/tickets/create")
    public TicketCreateResponse createTicket(@RequestBody TicketCreateRequest request, HttpSession session) {
        TicketDTO ticket = createTicketDTO(request, SessionUtils.getClientId(session));
        TicketCreateResponse response = new TicketCreateResponse();
        response.setTicketPK(userService.createTicket(ticket));
        return response;
    }

    /**
     * 티켓 업데이트 ( 카드결제 유무, 세금계산서 발행유무, 코멘트 )
     *
     * @param request
     * @param session
     */
    @LoginCheck
    @PutMapping("/tickets/normal/update")
    public void updateTicket(@RequestBody TicketUpdateRequest request, HttpSession session) {
        TicketDTO ticket = createTicketDTOForUpdate(request, SessionUtils.getClientId(session));
        userService.updateTicket(ticket);
    }

    /**
     * 티켓 조회
     *
     * @param session
     * @return
     */
    @LoginCheck
    @GetMapping("/tickets")
    public TicketsResponse tickets(HttpSession session) {
        TicketsResponse response = new TicketsResponse();
        List<TicketBiddingDTO> ticketBiddings = userService.getTicketBiddings(SessionUtils.getClientId(session));
        response.setTickets(ticketBiddings);
        return response;
    }

    /**
     * 티켓 취소
     * ticketType=0&cancelReason=2
     */

    @DeleteMapping("/tickets/{ticketId}")
    public void cancelTicket(@PathVariable("ticketId") long ticketId, @RequestBody TicketCancelRequest request, HttpSession session) {
        long clientId = SessionUtils.getClientId(session);
        userService.cancelTicket(clientId, ticketId, request.getCancelReason());
    }

    private TicketDTO createTicketDTO(TicketCreateRequest request, long clientId) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setSrcName(request.getSrcName());
        ticketDTO.setClientId(clientId);
        ticketDTO.setSrcAddress(request.getSrcAddress());
        ticketDTO.setSrcLatitude(request.getSrcLatitude());
        ticketDTO.setSrcLongitude(request.getSrcLongitude());
        ticketDTO.setDstName(request.getDstName());
        ticketDTO.setDstAddress(request.getDstAddress());
        ticketDTO.setDstLatitude(request.getDstLatitude());
        ticketDTO.setDstLongitude(request.getDstLongitude());
        ticketDTO.setTripTypeId(request.getTripType());
        ticketDTO.setDepartDate(request.getDepartDate());
        ticketDTO.setReturnDate(request.getReturnDate());
        ticketDTO.setTogether(request.getTogether());
        ticketDTO.setUserCnt(request.getUserCnt());
        ticketDTO.setCard(request.getCard());
        ticketDTO.setBusTypeId(request.getBusType());
        ticketDTO.setTaxReceipt(request.getTaxReceipt());
        ticketDTO.setStopover("");
        // TODO : 만료기한 로직 추가
        ticketDTO.setExpireDate(request.getDepartDate().plusDays(5));
        ticketDTO.setComment(request.getComment());
        return ticketDTO;
    }

    private TicketDTO createTicketDTOForUpdate(TicketUpdateRequest request, long clientId) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(request.getTicketPK());
        ticketDTO.setClientId(clientId);
        ticketDTO.setCard(request.getCard());
        ticketDTO.setTaxReceipt(request.getTaxReceipt());
        ticketDTO.setComment(request.getComment());
        return ticketDTO;
    }

    // ============= REQUEST =================

    @Setter
    @Getter
    @ToString
    private static class TicketCreateRequest {

        private String srcName;
        private String srcAddress;
        private double srcLatitude;
        private double srcLongitude;

        private String dstName;
        private String dstAddress;
        private double dstLatitude;
        private double dstLongitude;

        private int tripType;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime departDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime returnDate;
        private int together;
        private int userCnt;

        private int card;
        private int busType;
        private int taxReceipt;
        private double suggestPrice;
        private String comment;
    }

    @Getter
    @Setter
    private static class TicketUpdateRequest {
        private long ticketPK;
        private int card;
        private int taxReceipt;
        private String comment;
    }

    @Getter
    @Setter
    private static class TicketCancelRequest {
        private int ticketType;
        private TicketDTO.CancelReason cancelReason;
    }

    // ============= RESPONSE =================

    @Getter
    @Setter
    private static class TicketCreateResponse {
        private long ticketPK;
    }

    @Getter
    @Setter
    private static class TicketsResponse {
        private List<TicketBiddingDTO> tickets;
    }


}
