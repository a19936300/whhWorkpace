package com.binbinxiu.whh.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by c_panzhimin on 2019/12/17.
 * 接口返回数据模型
 */
@Data
@ApiModel("接口返回数据列表模型")
public class ResponseModelListVo<T> {

    @ApiModelProperty(value = "状态码",required = true,example = "success",position = 1)
    protected String code;

    @ApiModelProperty(value = "信息",position = 2)
    protected String message;

    @ApiModelProperty(value = "列表数据",position = 3)
    protected List<T> data;

    public ResponseModelListVo() {
    }

    public ResponseModelListVo(String code, List<T> data) {
        this.code = code;
        this.data = data;
    }
}
