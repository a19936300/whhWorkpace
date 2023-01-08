package com.binbinxiu.whh.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by c_panzhimin on 2019/12/17.
 * 接口返回数据模型
 */
@Data
@ApiModel("接口返回单项数据模型")
public class ResponseModelVo<T> {

    @ApiModelProperty(value = "状态码",required = true,example = "success",position = 1)
    protected String code;

    @ApiModelProperty(value = "信息",position = 2)
    protected String message;

    @ApiModelProperty(value = "单个数据",position = 3)
    private T data;

}
