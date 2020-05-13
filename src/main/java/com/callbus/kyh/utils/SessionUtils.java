package com.callbus.kyh.utils;

import java.util.Random;

public class SessionUtils {

    //인스턴스화 방지
    private SessionUtils() {
    }

    public static String generateCertNumber() {
        Random random = new Random();
        String certNumber = "";

        for (int i = 0; i < 4; i++) {
            String digit = Integer.toString(random.nextInt(10));
            certNumber+= digit;
        }

        return certNumber;

    }
}
