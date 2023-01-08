package com.binbinxiu.whh.base.api.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author wzd
 */
@Setter
@Getter
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 8143951872076866387L;
    private Long id;
    /**
     * 分公司代码
     */
    private String unitCode;

    /**
     * 人员代码
     */
    private String handlerCode;

    /**
     * 人员名称
     */
    private String handlerName;

    /**
     * 人员工号
     */
    private String workCode;

    /**
     * 部门组
     */
    private String deptGroupCode;

    /**
     * 所属科室
     */
    private String sectionCode;

    /**
     * 加密密码
     */
    private String password;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 登录令牌
     */
    private String token;

    /**
     * 角色列表
     */
    private List<RoleVo> roleList;

    /**
     * P13账号
     */
    private String cpicUID;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * FPB 模块 定制 跳转地址 没有需求可以不传
     * 跳转 M2 司外用户跳转地址
     */
    private String url;
}
