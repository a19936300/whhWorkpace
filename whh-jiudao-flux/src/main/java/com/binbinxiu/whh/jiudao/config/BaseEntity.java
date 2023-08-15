package com.binbinxiu.whh.jiudao.config;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
public class BaseEntity {
    private static final long serialVersionUID = -2878810822703926618L;

    @TableId(type = IdType.AUTO)
    protected Long id;

    @TableField(value = "revision",fill = FieldFill.INSERT)
    private Integer revision;

    @TableField(value = "created_time",fill = FieldFill.INSERT)
    protected String createdTime;

    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    protected String updatedTime;

    @TableField(value = "created_by",fill = FieldFill.INSERT)
    protected Long createdBy;

    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    protected Long updatedBy;

//    @TableLogic(value = "0",delval = "1")
//    @TableField(value = "is_delete",fill = FieldFill.INSERT)
//    protected String isDelete;

}
