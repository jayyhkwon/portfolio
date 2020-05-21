package com.callbus.kyh.controller;


import com.callbus.kyh.dto.client.PlayerType;
import com.callbus.kyh.error.InvalidCertNumberException;
import com.callbus.kyh.error.UnauthorizedException;
import com.callbus.kyh.service.AccountService;
import com.callbus.kyh.service.PushService;
import com.callbus.kyh.service.UserService;
import com.callbus.kyh.utils.SessionUtils;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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

    private final UserService userService;

    @Value("${expire.client.session}")
    private int maxInactiveIntervalInSeconds;


    /**
     * 인증번호 전송
     * 사용자에게 인증번호를 보내고 redis에 인증번호를 저장한다.
     *
     * @param request
     * @throws FirebaseMessagingException
     */
    @PostMapping("/cert/send")
    public void send(@RequestBody ClientLoginRequest request) throws FirebaseMessagingException {
        String certNumber = accountService.saveCertNumber(request.getPhoneNumber());
        pushService.pushCertNumber(request.getPhoneNumber(), certNumber);
    }

    /**
     * 회원가입 (일반 고객만 구현)
     * @param request
     */
    @PostMapping("/join")
    public void join(@RequestBody ClientLoginRequest request) {
        if (request.getPlayerType() == PlayerType.CLIENT) {
            accountService.joinAsClient(request.getPhoneNumber());
        }


    }

    /**
     * 로그인
     * @param loginRequest
     * @param request
     * @param response
     */
    @PostMapping("/login")
    public void login(@RequestBody ClientLoginRequest loginRequest,
                      HttpServletRequest request,
                      HttpServletResponse response) {

        String certNumber = accountService.getCertNumber(loginRequest.getPhoneNumber());

        if (!certNumber.equals(loginRequest.getCertNum()))
            throw new InvalidCertNumberException("인증번호 불일치");

        long clientId = userService.getClientIdByPhoneNumber(loginRequest.getPhoneNumber());
        if (clientId == 0)
            throw new UnauthorizedException("회원 가입되지 않은 번호입니다");

        HttpSession session = request.getSession();
        SessionUtils.setClientId(session, clientId);
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
    @ToString
    private static class ClientLoginRequest {
        private String phoneNumber;
        private String certNum;
        private PlayerType playerType;
    }


}
