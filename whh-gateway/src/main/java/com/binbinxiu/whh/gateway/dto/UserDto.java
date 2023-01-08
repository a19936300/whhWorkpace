package com.binbinxiu.whh.gateway.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 * @author wzd
 */
@Data
public class UserDto {

    private String id;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "分公司代码不能为空")
    private String unitCode;

    @NotBlank(message = "人员代码不能为空")
    private String handlerCode;

    /**
     * 请求唯一的uuid
     */
    private String uuid;

    /**
     * 验证码
     */
    private String verifyCode;
}
