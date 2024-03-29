package com.binbinxiu.whh.jiudao.config;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回到页面对象
 *
 * @author wzd
 */
@Data
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 350939574321573616L;

    /**
     * 消息状态
     */
    private String status = "";

    /**
     * 交互结果消息描述
     */
    private String msg = "";

    /**
     * 消息体
     */
    private transient T data;

    /**
     * 消息代码
     */
    private String code = "";

    /**
     * 成功默认消息
     *
     * @return
     */
    private ResponseVO() {
        this.status = SysConstant.STATUS.Valid;
        this.msg = SysConstant.MSG.SUCCESS;
        this.code = SysConstant.SYS_CODE.STATUS_SUCCESS;
    }

    /**
     * 成功默认消息
     *
     * @return
     */
    public static ResponseVO successInstance() {
        return new ResponseVO();
    }

    /**
     * 成功-返回数据
     *
     * @param data
     * @return
     */
    public static ResponseVO successInstance(Object data) {
        ResponseVO res = successInstance();
        res.setData(data);
        return res;
    }

    /**
     * 失败错误消息
     *
     * @return
     */
    public static ResponseVO errorInstance() {
        ResponseVO res = successInstance();

        res.setStatus(SysConstant.STATUS.Invalid);
        res.setCode(SysConstant.SYS_CODE.STATUS_ERROR);
        res.setMsg(SysConstant.MSG.ERROR);

        return res;
    }

    /**
     * 失败错误消息
     *
     * @param msg
     * @return
     */
    public static ResponseVO errorInstance(String msg) {
        ResponseVO res = successInstance();

        res.setStatus(SysConstant.STATUS.Invalid);
        res.setCode(SysConstant.SYS_CODE.STATUS_ERROR);
        res.setMsg(StrUtil.isBlank(msg) ? SysConstant.MSG.ERROR : msg);

        return res;
    }

    /**
     * 失败错误消息
     *
     * @param code 消息代码
     * @return
     */
    public static ResponseVO errorInstance(String code, String msg) {
        ResponseVO res = successInstance();

        res.setStatus(SysConstant.STATUS.Invalid);
        res.setCode(StrUtil.isBlank(code) ? SysConstant.SYS_CODE.STATUS_ERROR : code);
        res.setMsg(StrUtil.isBlank(msg) ? SysConstant.MSG.ERROR : msg);

        return res;
    }

    /**
     * 失败错误消息
     *
     * @param status 状态代码
     * @param code   消息代码
     * @param msg
     * @return
     */
    public static ResponseVO errorInstance(String status, String code, String msg) {
        ResponseVO res = successInstance();

        res.setStatus(StrUtil.isBlank(status) ? SysConstant.STATUS.Invalid : status);
        res.setCode(StrUtil.isBlank(code) ? SysConstant.SYS_CODE.STATUS_ERROR : code);
        res.setMsg(StrUtil.isBlank(msg) ? SysConstant.MSG.ERROR : msg);

        return res;
    }

    /**
     * 获取指定类型的对象
     *
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getData(Class<T> cls) {
        return (T) this.getData();
    }


}
