package com.callbus.kyh.controller;


import com.callbus.kyh.error.InvalidCertNumberException;
import com.callbus.kyh.service.AccountService;
import com.callbus.kyh.service.PushService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final PushService pushService;


    @PostMapping("/cert/send")
    public void send(@RequestBody ClientLoginRequest request) throws FirebaseMessagingException {
        String certNumber = accountService.saveCertNumber(request.getPhoneNumber());
        pushService.pushCertNumber(request.getPhoneNumber(), certNumber);
    }

    @PostMapping("/login")
    public void login(@RequestBody ClientLoginRequest request) {
        String certNumber = accountService.getCertNumber(request.getPhoneNumber());

        if (!certNumber.equals(request.getCertNum()))
            throw new InvalidCertNumberException("인증번호 불일치");
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
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
