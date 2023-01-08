package com.binbinxiu.whh.gateway.security.model;

/**
 * @Author wzd
 */
public class TokenTime {

    /**
     * 有效时长
     * 一天24小时
     */
    //private long expTime = 24 * 60 * 60 * 1000;


    /**
     * token的有效时长
     */
//    public static long tokenExpTime = 60 * 60 * 1000;
    //20221020 caozy 修改 teken 有效时间 为 30分钟
    public static long tokenExpTime = 30 * 60 * 1000;

    /**
     * token需要续约的时间
     */
    public static long renewTime = 15 * 60 * 1000;
}
