package com.callbus.kyh.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로그인 상태를 확인한다.
 * 로그인 상태를 확인하여 로그인 되지 않은 경우 예외를 발생시킨다.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {
}
