package com.binbinxiu.whh.gateway.constant.exception;

/**
 * 验证码异常
 */
public class CaptchaException extends RuntimeException {

    public CaptchaException(String message) {
        super(message);
    }
}
