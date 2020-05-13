package com.callbus.kyh.service;

import com.callbus.kyh.dao.RedisDAO;
import com.callbus.kyh.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private final RedisDAO redisDAO;

    public String getCertNumber(String phoneNumber) {
        return redisDAO.getCertNumber(phoneNumber);
    }

    public String saveCertNumber(String phoneNumber) {

        String existCertNumber = redisDAO.getCertNumber(phoneNumber);
        if (!StringUtils.isEmpty(existCertNumber)) { // 키가 존재하는 경우
            redisDAO.initCertNumberExpireTime(phoneNumber);
            return existCertNumber;
        }

        String certNumber = SessionUtils.generateCertNumber();
        redisDAO.saveCertNumber(phoneNumber, certNumber);
        return certNumber;
    }
}
