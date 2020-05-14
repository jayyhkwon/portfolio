package com.callbus.kyh.controller;

import com.callbus.kyh.service.AccountService;
import com.callbus.kyh.service.PushService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccountControllerTest {

    AccountController accountController;

    @MockBean
    AccountService accountService;

    @MockBean
    PushService pushService;

    String request;

    @Autowired
    MockMvc mockMvc;


    @Before
    public void setUp() {
        request = "{\"phoneNumber\":\"01012345678\",\"certNum\":\"1234\",\"playerType\":\"2\"}";
//        accountController = new AccountController(accountService, pushService);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController)
//                                        .setControllerAdvice(new ErrorController())
//                                        .build();
    }

    @Test
    public void login_인증번호_일치() throws Exception {

        given(accountService.getCertNumber(anyString())).willReturn("1234");

        mockMvc.perform(post("/account/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void login_인증번호_불일치() throws Exception {

        given(accountService.getCertNumber(anyString())).willReturn("1111");

        mockMvc.perform(post("/account/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());

    }

}