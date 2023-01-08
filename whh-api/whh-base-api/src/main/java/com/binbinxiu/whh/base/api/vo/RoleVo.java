package com.binbinxiu.whh.base.api.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author wzd
 */
@Setter
@Getter
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 830405002197664915L;

    private Long id;
    /**
     * 资源所属模块
     */
    private String moduleType;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色级别
     */
    private String roleLevel;
}
