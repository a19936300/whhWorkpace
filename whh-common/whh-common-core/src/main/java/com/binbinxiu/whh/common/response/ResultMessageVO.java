package com.binbinxiu.whh.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 微信小程序接口返回对象
 * @author: c_zhouaihua-002
 * @create: 2022-03-17 17:48
 **/
@Data
public class ResultMessageVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;			//1：成功，0：失败
    private String code = "0000";	//响应成功的代码是： 0000
    private String message;			//消息
    private transient T responseBody;	//对象

    public static ResultMessageVO resultMessage(String status, String code, String message, Object responseBody) {
        ResultMessageVO resultMessage = new ResultMessageVO();
        resultMessage.setStatus(status);
        resultMessage.setMessage(message);
        resultMessage.setCode(code);
        resultMessage.setResponseBody(responseBody);
        return resultMessage;
    }

    public ResultMessageVO() {

    }
}

