package com.callbus.kyh.utils;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static final String CLIENT_ID = "LOGIN_CLIENT_ID";

    //인스턴스화 방지
    private SessionUtils() {
    }

    public static void setClientId(HttpSession session, long id) {
        session.setAttribute(CLIENT_ID, id);
    }

    public static long getClientId(HttpSession session) {
        return (long) session.getAttribute(CLIENT_ID);
    }
}
