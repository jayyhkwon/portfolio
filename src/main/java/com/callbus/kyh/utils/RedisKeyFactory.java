package com.callbus.kyh.utils;

import java.util.Random;

public class RedisKeyFactory {

    private RedisKeyFactory(){}

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
