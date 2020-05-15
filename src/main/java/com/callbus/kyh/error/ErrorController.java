package com.callbus.kyh.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
 * 스프링에서 지원하는 예외처리 어노테이션
 *
 * @ExceptionHandler({Exception1.class, Exception2.class})
 * - @Controller, @RestController에서 사용 가능
 * - 컨트롤러 클래스 내에서 발생하는 예외를 해당 어노테이션이 적용된 메서드를 통해 처리한다.
 *
 * @ControllerAdvice ( @RestControllerAdvice )
 * - 모든 @Controller 또는 @RestController에 적용되는 공통클래스를 만들 때 사용한다.
 */


@RestControllerAdvice
public class ErrorController {

    private String getSimepleName(Exception e){
        return e.getClass().getSimpleName();
    }



    // 401 Unauthorized
    // 비록 HTTP 표준에서는 "미승인(unauthorized)"를 명확히 하고 있지만, 의미상 이 응답은 "비인증(unauthenticated)"을 의미합니다.
    // 클라이언트는 요청한 응답을 받기 위해서는 반드시 스스로를 인증해야 합니다.
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getSimpleName());
    }

    /**
     * 로그인 시 승객, 기사, 버스 회사가 아닌 경우
     * @param e
     * @return
     */

    // 401 Unauthorized
    // 비록 HTTP 표준에서는 "미승인(unauthorized)"를 명확히 하고 있지만, 의미상 이 응답은 "비인증(unauthenticated)"을 의미합니다.
    // 클라이언트는 요청한 응답을 받기 위해서는 반드시 스스로를 인증해야 합니다.
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidFormatException(InvalidFormatException e){
        return new ErrorResponse("올바른 접근이 아닙니다", getSimepleName(e));
    }

    // 401 Unauthorized
    // 비록 HTTP 표준에서는 "미승인(unauthorized)"를 명확히 하고 있지만, 의미상 이 응답은 "비인증(unauthenticated)"을 의미합니다.
    // 클라이언트는 요청한 응답을 받기 위해서는 반드시 스스로를 인증해야 합니다.
    @ExceptionHandler(InvalidCertNumberException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidCertNumberException(InvalidCertNumberException e){
        return new ErrorResponse(e.getMessage(), getSimepleName(e));
    }

}
