package com.callbus.kyh.controller;


import com.callbus.kyh.service.AccountService;
import com.callbus.kyh.service.PushService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.ref.PhantomReference;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    PushService pushService;


    @PostMapping("/cert/send")
    public void send(@RequestBody ClientLoginRequest request) throws FirebaseMessagingException {
        String certNumber = accountService.saveCertNumber(request.getPhoneNumber());
        pushService.pushCertNumber(request.getPhoneNumber(), certNumber);
    }

    @PostMapping("/login")
    public void login(@RequestBody ClientLoginRequest request) {
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public static class ClientLoginRequest {
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
