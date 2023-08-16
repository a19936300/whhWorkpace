package com.binbinxiu.whh.jiudao.config;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BaseEntity {
    private static final long serialVersionUID = -2878810822703926618L;

    @Id
    protected Long id;

    private Integer revision;

    protected Data createdTime;

    protected Data updatedTime;

    protected Long createdBy;

    protected Long updatedBy;

//    @TableLogic(value = "0",delval = "1")
//    @TableField(value = "is_delete",fill = FieldFill.INSERT)
//    protected String isDelete;

}
