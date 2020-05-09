package com.callbus.kyh.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final String CLIENT_TOKEN = "evVxPHaiTt6o37_of1l8ha:APA91bHPstR-28IPqZSrZ674qL2KFS1KsZ2hOEhLc8DsQ69QARag1N04WIj8lAUJDL_zwdCokz-rsJFHKnakJ24jUcGDDikxXvdvoMV3VpHE4qWbESaCAj_HhXvZV5r8RE0VyQoH8Xxz";
    private static final String SERVER_KEY = "AAAAHC0s3BI:APA91bF3rhTp7hj7gign_8sjxyO6SBocZdvOi7cLnsTitTbqn4CzHOTJfq5LkdFoktnF0K0QGjAPApvRodRtCJBtcjQ_MZSb7CTjG_BdKi_cWS6qJMbJP0ruU_3NTkZeCiiNPQpG2X8";

    @Value("${fcm.key.path}")
    private String FCM_PRIVATE_KEY_PATH;

    /**
     * FCM 기본 설정을 진행한다.
     */
    @PostConstruct
    public void init() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials
                                    .fromStream(new ClassPathResource(FCM_PRIVATE_KEY_PATH).getInputStream()))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/cert/send")
    public String send(@RequestBody ClientLoginRequest request) throws FirebaseMessagingException {

        // TODO : 레디스 연동하여 세션처리

        Message message = Message.builder()
                .setAndroidConfig(
                        AndroidConfig.builder()
                                .setNotification(AndroidNotification.builder()
                                        .setTitle("테스트")
                                        .setBody("푸시")
                                        .build()
                                )
                                .build())
                .setToken(CLIENT_TOKEN)
                .build();

        FirebaseMessaging.getInstance().send(message);
        return "aaa";
    }

    @PostMapping("/login")
    public void login(@RequestBody ClientLoginRequest request) {
        //TODO : 레디스에서 토큰 값 가져와서 비교

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
