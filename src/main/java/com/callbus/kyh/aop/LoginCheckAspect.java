package com.callbus.kyh.aop;


import com.callbus.kyh.error.UnauthorizedException;
import com.callbus.kyh.utils.SessionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class LoginCheckAspect {


    /**
     * session에서 로그인 여부를 체크한다.
     * 로그인이 되어 있지 않은 경우 예외를 발생시킨다.
     * @param jp
     */
    @Before(value = "@annotation(com.callbus.kyh.aop.LoginCheck)")
    public void loginCheck(JoinPoint jp) {

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String id = SessionUtils.getLoginMemberId(session);

        if (StringUtils.isEmpty(id)) {
            throw new UnauthorizedException("로그인이 필요합니다");
        }
    }
}
