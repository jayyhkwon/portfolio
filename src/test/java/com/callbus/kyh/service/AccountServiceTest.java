package com.callbus.kyh.service;

import com.callbus.kyh.dao.RedisDAO;
import com.callbus.kyh.error.DuplicatedPhoneNumberException;
import com.callbus.kyh.mapper.AccountMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private RedisDAO redisDAO;

    @Mock
    private AccountMapper mapper;

    @Before
    public void init() {
        accountService = new AccountService(redisDAO, mapper);
    }

    @Test
    public void saveCertNumber_인증번호가_존재하는_경우_유효기간_연장() {
        given(accountService.getCertNumber(anyString())).willReturn("1234");

        accountService.saveCertNumber(anyString());

        verify(redisDAO).initCertNumberExpireTime(anyString());
    }

    @Test
    public void saveCertNumber_인증번호가_없는경우_생성하여_레디스에_저장() {
        given(accountService.getCertNumber(anyString())).willReturn(null);

        accountService.saveCertNumber(anyString());

        verify(redisDAO, never()).initCertNumberExpireTime(anyString());
        verify(redisDAO).saveCertNumber(anyString(), anyString());
    }

    @Test
    public void joinAsClient_성공() {
        given(mapper.phoneNumberCheck(anyString())).willReturn(0);
        given(mapper.joinAsClient(any())).willReturn(1);

        accountService.joinAsClient(anyString());

        verify(mapper).joinAsClient(any());
    }

    @Test(expected = DuplicatedPhoneNumberException.class)
    public void joinAsClient_중복아이디_존재하는경우() {
        given(mapper.phoneNumberCheck(anyString())).willReturn(1);

        accountService.joinAsClient(anyString());

        verify(mapper, never()).joinAsClient(any());
    }


}