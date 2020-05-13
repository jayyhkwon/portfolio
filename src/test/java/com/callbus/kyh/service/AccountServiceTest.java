package com.callbus.kyh.service;

import com.callbus.kyh.dao.RedisDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private RedisDAO redisDAO;

    private String phoneNumber = "01012345678";

    @Before
    public void init() {
        accountService = new AccountService(redisDAO);
    }

    @Test
    public void saveCertNumber_인증번호가_존재하는_경우_유효기간_연장() {
        given(accountService.getCertNumber(phoneNumber)).willReturn("1234");

        accountService.saveCertNumber(phoneNumber);
        verify(redisDAO).initCertNumberExpireTime(phoneNumber);
    }

    @Test
    public void saveCertNumber_인증번호가_없는경우_생성하여_레디스에_저장() {
        given(accountService.getCertNumber(phoneNumber)).willReturn(null);

        accountService.saveCertNumber(phoneNumber);
        verify(redisDAO, never()).initCertNumberExpireTime(phoneNumber);
        verify(redisDAO).saveCertNumber(anyString(), anyString());

    }


}