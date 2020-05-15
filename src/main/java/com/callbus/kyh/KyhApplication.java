package com.callbus.kyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableRedisHttpSession(redisNamespace = "callbus.session", maxInactiveIntervalInSeconds = 60 * 60 * 24)
/*
 * @EnableRedisHttpSession 애노테이션 사용시 springSessionRepositoryFilter Bean에서 session을 관리한다.
 * HttpSession을 랩핑한 HttpSessionWrapper 클래스로 redis에 세션정보를 저장하고 관리한다.
 * maxInactiveIntervalInSeconds로 세션 만료시간을 설정할 수 있으며, 만료시간 + 5분 후 세션이 redis에서 삭제된다.(https://docs.spring.io/spring-session/docs/current/reference/html5/#api-redisindexedsessionrepository)
 * 세션을 별도의 redis에서 관리함으로 웹 서버를 다중화 하더라도 세션정보를 하나로 관리할 수 있게 된다. (글로벌 캐쉬)
 */
public class KyhApplication {

    public static void main(String[] args) {
        SpringApplication.run(KyhApplication.class, args);
    }
}
