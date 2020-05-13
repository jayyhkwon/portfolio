package com.callbus.kyh.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class PushService {

    @Value("${fcm.key.client-token}")
    private String CLIENT_TOKEN;

    @Value("${fcm.key.server-key}")
    private String SERVER_KEY;

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

    public void pushCertNumber(String phoneNumber, String certNumber) throws FirebaseMessagingException {
        sendFCMMessage(createFCMMessage(phoneNumber, certNumber));
    }

    private void sendFCMMessage(Message message) throws FirebaseMessagingException {
        FirebaseMessaging.getInstance().send(message);
    }

    private Message createFCMMessage(String phoneNumber, String certNum) {
        return Message.builder()
                .setAndroidConfig(
                        AndroidConfig.builder()
                                .setNotification(
                                        AndroidNotification.builder()
                                                .setTitle("콜버스")
                                                .setBody(String.format("콜버스 인증번호는 [%s] 입니다", certNum))
                                                .build()
                                )
                                .build())
                .setToken(createToken(phoneNumber))
                .build();
    }

    private String createToken(String phoneNumber) {
        //TODO : 휴대폰 번호로 토큰 생성
        return CLIENT_TOKEN;
    }
}
