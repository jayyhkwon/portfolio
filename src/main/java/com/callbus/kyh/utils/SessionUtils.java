package com.callbus.kyh.utils;

import javax.servlet.http.HttpSession;
import java.util.Random;

public class SessionUtils {

    private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";

    //인스턴스화 방지
    private SessionUtils() {
    }

    public static void setLoginMemberId(HttpSession session){
        session.setAttribute(LOGIN_MEMBER_ID, session.getId());
    }

    public static String getLoginMemberId(HttpSession session){
        return (String) session.getAttribute(LOGIN_MEMBER_ID);
    }
}
