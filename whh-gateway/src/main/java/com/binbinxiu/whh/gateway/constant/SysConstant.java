package com.binbinxiu.whh.gateway.constant;

/**
 * 系统级常量定义
 *
 * @author wzd
 */
public interface SysConstant {

    /**
     * 请求头key名称
     */
    class Header {

        /**
         * token
         */
        public static final String TOKEN = "token";

        /**
         * 用户id key
         */
        public static String USER_ID = "userId";

        /**
         * 用户名 key
         */
        public static String USER_NAME = "userName";

        /**
         * 证件号码
         */
        public static String CERT_NO = "certNo";

        /**
         * 证件类型
         */
        public static String CERT_TYPE = "certType";

        /**
         * 手机号码
         */
        public static String PHONE_NUMBER = "phoneNumber";
    }

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

    /**
     * 逻辑删除的记录状态
     */
    public class RECORD_STATUS {
        /**
         * 已删除
         */
        public static final String IS_DELETED = "1";
        /**
         * 未删除
         */
        public static final String NOT_DELETED = "0";
    }

    /**
     * 常量是和否
     */
    public class YesOrNo {
        /**
         * 已删除
         */
        public static final String YES = "1";

        /**
         * 未删除
         */
        public static final String NO = "0";

    }

    class UserToken {
        public static final String SURVEY_RSA_K = "SURVEY_RSA_K";
        public static final String SURVEY_PUBLIC_K = "SURVEY_PUBLIC_K";
        public static final String SURVEY_PRIVATE_K = "SURVEY_PRIVATE_K";

        public static final String REPORT_RSA_K = "REPORT_RSA_K";
        public static final String REPORT_PUBLIC_K = "REPORT_PUBLIC_K";

        public static final String AUTH_PUBLIC_K = "AUTH_PUBLIC_K";
        public static final String AUTH_PRIVATE_K = "AUTH_PRIVATE_K";

        public static final String VALID_TOKEN = "LTC_VALID_TOKEN";
    }

     class MODULE_TYPE {
        /**
         * 长护
         */
        public static final String LTC = "ltc";
        /**
         * 项目管理
         */
        public static final String PROJECT = "project";
        /**
         * 调查
         */
        public static final String SURVEY = "survey";
        /**
         * 报案
         */
        public static final String REPORT = "report";
        /**
         * 通用
         */
        public static final String COMMON = "common";

    }


}
