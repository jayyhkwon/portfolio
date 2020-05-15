package com.callbus.kyh.controller;


import com.callbus.kyh.error.InvalidCertNumberException;
import com.callbus.kyh.service.AccountService;
import com.callbus.kyh.service.PushService;
import com.callbus.kyh.utils.SessionUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final PushService pushService;

    @Value("${expire.client.session}")
    private int maxInactiveIntervalInSeconds;


    @PostMapping("/cert/send")
    public void send(@RequestBody ClientLoginRequest request) throws FirebaseMessagingException {
        String certNumber = accountService.saveCertNumber(request.getPhoneNumber());
        pushService.pushCertNumber(request.getPhoneNumber(), certNumber);
    }

    @PostMapping("/login")
    public void login(@RequestBody ClientLoginRequest loginRequest,
                      HttpServletRequest request,
                      HttpServletResponse response) {

        String certNumber = accountService.getCertNumber(loginRequest.getPhoneNumber());

        if (!certNumber.equals(loginRequest.getCertNum()))
            throw new InvalidCertNumberException("인증번호 불일치");

        HttpSession session = request.getSession();
        SessionUtils.setLoginMemberId(session);
        addCookies(request, response);
    }


    private void addCookies(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Cookie sessionid = new Cookie("UUID", session.getId());
        sessionid.setHttpOnly(true); // javascript로 세션 탈취 방지
        sessionid.setMaxAge(maxInactiveIntervalInSeconds);
        sessionid.setPath("/");
        response.addCookie(sessionid);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static class ClientLoginRequest {
        private String phoneNumber;
        private String certNum;
        private PlayerType playerType;
    }

    public enum PlayerType {
        @JsonProperty("1")
        CLIENT,
        @JsonProperty("2")
        BUS_DRIVER,
        @JsonProperty("3")
        BUS_COMPANY;
    }

}
