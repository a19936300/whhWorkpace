package com.binbinxiu.whh.jiudao.config;

public class SysConstant {
    /**
     * 状态
     */
    public class STATUS {
        /**
         * 有效/成功
         */
        public static final String Valid = "1";

        /**
         * 失效/失败
         */
        public static final String Invalid = "0";

    }

    /**
     * 系统级自身消息代码
     */
    public class SYS_CODE {

        /**
         * token 错误
         */
        public static final String TOKEN_ERROR = "4003";

        /**
         * 成功
         */
        public static final String STATUS_SUCCESS = "2000";

        /**
         * 失败
         */
        public static final String STATUS_ERROR = "5000";

        /**
         * 用户过期
         */
        public static final String STATUS_OVER = "9999";

        /**
         * 业务异常
         */
        public static final String SERVICE_EXP = "6000";

        /**
         * 参数校验异常
         */
        public static final String VALID_EXP = "6001";

        /**
         * 流程校验异常
         */
        public static final String BPM_VALID_EXP = "7000";

    }

    public class MSG {
        /**
         * 操作成功
         */
        public static final String SUCCESS = "操作成功";

        /**
         * 系统错误，请联系管理员
         */
        public static final String ERROR = "系统错误，请联系管理员";
    }




}
