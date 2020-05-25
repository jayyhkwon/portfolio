package com.callbus.kyh.controller;

import com.callbus.kyh.dto.ticket.TicketDTO;
import com.callbus.kyh.service.UserService;
import com.callbus.kyh.utils.SessionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.config.location=classpath:/application.yml")
@PowerMockRunnerDelegate(SpringRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(SessionUtils.class)
@AutoConfigureMockMvc
/*
 * static class를 mocking 하기 위해 powerMock를 사용하였으며,
 * Spring 환경에서 테스트를 하기 위해서 PowerMockRunnerDelegate 애노테이션을 추가하였다.
 * @PrepareForTest 애노테이션에 mocking 하고싶은 static class를 추가한다.
 */
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Mock
    HttpSession session;

    @Autowired
    MockMvc mockMvc;

    String request = "{\"srcName\": \"신촌역 2호선\"," +
            "\t\"srcAddress\": \"서울시\",\n" +
            "\t\"srcLatitude\": 37.55519907036358,\n" +
            "\t\"srcLongitude\": 126.93698075917935,\n" +
            "\t\"dstName\": \"수원역\",\n" +
            "\t\"dstAddress\": \"asdasd\",\n" +
            "\t\"dstLatitude\": 37.2657903079673,\n" +
            "\t\"dstLongitude\": 127.000094700292,\n" +
            "\t\"tripType\": \"1\",\n" +
            "\t\"departDate\": \"2020-05-29 07:00:00\",\n" +
            "\t\"returnDate\": \"2020-05-29 22:00:00\",\n" +
            "\t\"together\": 0,\n" +
            "\t\"userCnt\": 12,\n" +
            "\t\"purpose\": \"8\",\n" +
            "\t\"clientRole\": \"0\",\n" +
            "\t\"card\": 0,\n" +
            "\t\"busType\": \"1\",\n" +
            "\t\"taxReceipt\": 0,\n" +
            "\t\"suggestPrice\": \"\",\n" +
            "\t\"comment\": \"\"\n" +
            "}";

    String updateRequest = "{\n" +
            "\t\"card\": 1,\n" +
            "\t\"taxReceipt\": 0,\n" +
            "\t\"comment\": \"\"\n" +
            "}";

    String response = "{\n" +
            "    \"ticketPK\": 10\n" +
            "}";

    String cancelRequest = "{\"ticketType\": 0, \"cancelReason\": 2}";
    @Before
    public void setUp() {
        PowerMockito.mockStatic(SessionUtils.class);
        PowerMockito.when(SessionUtils.getClientId(session)).thenReturn(1L);
    }


    @Test
    public void ticketCreate_티켓생성() throws Exception {

        mockMvc.perform(post("/user/tickets/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());

        verify(userService).createTicket(any());
    }

    @Test
    public void ticketUpdate_티켓업데이트() throws Exception {
        mockMvc.perform(put("/user/tickets/{ticketId}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateRequest))
                .andExpect(status().isOk());

        verify(userService).updateTicket(any());
    }

    @Test
    public void tickets_티켓조회() throws Exception {
        mockMvc.perform(get("/user/tickets"))
                .andExpect(status().isOk());

        verify(userService).getTicketBiddings(anyLong());
    }

    @Test
    public void ticketCancel_티켓취소() throws Exception {
        mockMvc.perform(delete("/user/tickets/{ticketId}",11L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(cancelRequest))
                .andExpect(status().isOk());

        verify(userService).cancelTicket(anyLong(), anyLong(), any(TicketDTO.CancelReason.class));
    }

}