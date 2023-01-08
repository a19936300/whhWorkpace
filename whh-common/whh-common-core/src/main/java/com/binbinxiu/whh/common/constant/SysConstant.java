package com.binbinxiu.whh.common.constant;

/**
 * 系统级常量定义
 */
public interface SysConstant {

    /**
     * 三级行政区域常量
     */
    class DIVISION {
        /**
         * 省份标识
         */
        public final static String PRO = "pro";
        /**
         * 市级标识
         */
        public final static String CITY = "city";
        /**
         * 区县标识
         */
        public final static String COUNTY = "county";
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
     * 枚举在数据库中的key
     */
    public class ENUM_NAME {
        /**
         * 个人证件类型
         */
        public static final String PersonalCertTypeEnum = "PersonalCertTypeEnum";
        /**
         * 状态
         */
        public static final String YesNoEnum = "YesNoEnum";
        /**
         * 调查结论
         */
        public static final String InvestigationConclusionEnum = "InvestigationConclusionEnum";
        /**
         * 健康险核心调查结论
         */
        public static final String InvResultEnum = "INV_RESULT";
        /**
         * 调查类型
         */
        public static final String SurveyTypeEnum = "SurveyTypeEnum";
        /**
         * 调查状态
         */
        public static final String SurveyStatusEnum = "SurveyStatusEnum";
        /**
         * 业务模块
         */
        public static final String ModuleEnum = "ModuleEnum";
        /**
         * 医疗机构等级代码
         */
        public static final String HospitalLevelEnum = "HospitalLevelEnum";
        /**
         * 医院等级所属等
         */
        public static final String HospitalClassEnum = "HospitalClassEnum";
        /**
         * 医院等级所属级
         */
        public static final String HospitalGradeEnum = "HospitalGradeEnum";

    }


    public class AUTH_ROLE_ENUM {
        /**
         * 调查员
         */
        public static final String SURVEYROLEENUM = "SURVEYROLEENUM";
        /**
         * 审核员
         */
        public static final String ASSESSORROLEENUM = "ASSESSORROLEENUM";
        /**
         * 调度员
         */
        public static final String DISTRIBUTIONROLEENUM = "DISTRIBUTIONROLEENUM";
        /**
         * 作业区域配置岗：
         */
        public static final String SURVEYSCOPEENUM="SURVEYSCOPEENUM";
    }

    public class MODULE_TYPE {
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
        /**
         * 慢病
         */
        public static final String PWC = "pwc";
        /**
         * 基础
         */
        public static final String BASE = "base";
        /**
         * 主数据
         */
        public static final String MAIN_DATA = "mainData";

        /**
         *  医保对接
         */
        public static final String DATA_EXCHANGE = "dataExchange";

        /**
         * 报案模块（意外&防贫保）
         */
        public static final String CASES="cases";
    }

    /**
     * 系统配置
     */
    public class SYSTEM_CONFIG{
        /**
         * 获取新增调查接口url
         */
        public static final String GET_SURVEYADDURL="GET_SURVEYADDURL";
        /**
         * 与老长护（报案）交互的公私钥 查询老保单列表使用
         */
        public static final String REPORT_RSA_K="REPORT_RSA_K";
        public static final String REPORT_PUBLIC_K="REPORT_PUBLIC_K";
        /**
         * 调查模块定义的公私钥
         */
        public static final String SURVEY_PUBLIC_K="SURVEY_PUBLIC_K";
        public static final String SURVEY_PRIVATE_K="SURVEY_PRIVATE_K";
        /**
         *  老报案模块与调查模块交互，调查模块定义的加密内容
         */
        public static final String SURVEY_RSA_K="SURVEY_RSA_K";

        /**
         * 获取巡查集合url
         */
        public static final String GET_SURVEYLIST="GET_SURVEYLIST";
        /**
         * 获取报案枚举
         */
        public static final String REPORT_GET_ENUM = "REPORT_GET_ENUM";
        /**
         * 获取长护枚举
         */
        public static final String LTC_GET_ENUM = "LTC_GET_ENUM";
        /**
         * 获取报案详情
         */
        public static final String REPORT_GET_EREPORTDETAIL = "REPORT_GET_EREPORTDETAIL";
        /**
         * 获取长护详情
         */
        public static final String LTC_GET_LTCDETAIL = "LTC_GET_LTCDETAIL";
        /**
         * 通知报案调查已完成
         */
        public static final String REPORT_SEND_SURVEYFINISH = "REPORT_SEND_SURVEYFINISH";
        /**
         * 获取健康险核心详情
         */
        public static final String SURVEY_GET_HEALTHYCOREDETAIL = "SURVEY_GET_HEALTHYCOREDETAIL";
        /**
         * 通知健康险核心调查已完成
         */
        public static final String SURVEY_SEND_HEALTHYCOREFINISH = "SURVEY_SEND_HEALTHYCOREFINISH";
        /**
         * 通知长护调查状态修改
         */
        public static final String LTC_SEND_SURVEYSTATUSCHANGED = "LTC_SEND_SURVEYSTATUSCHANGED";
        /**
         * 报案数据更新
         */
        public static final String REPORT_UPDATE_INFO = "REPORT_UPDATE_INFO";
        /**
         * 获取保单
         */
        public static final String REPORT_POLICYNO="REPORT_POLICYNO";

        /**
         * 登录页面
         */
        public static final String LOGIN_PAGE="LOGIN_PAGE";

        /**
         * 获取健康险核心查询报案详情权限token的url
         */
        public static final String GET_HEALTHY_CORE_TOKEN_URL = "GET_HEALTHY_CORE_TOKEN_URL";
        /**
         * 获取健康险核心查询报案详情权限token的分公司code
         */
        public static final String GET_HEALTHY_CORE_TOKEN_UNIT_CODE = "GET_HEALTHY_CORE_TOKEN_UNIT_CODE";
        /**
         * 获取健康险核心查询报案详情权限token的用户code
         */
        public static final String GET_HEALTHY_CORE_TOKEN_USER_CODE = "GET_HEALTHY_CORE_TOKEN_USER_CODE";

        /**
         * 获取健康险核心查询报案详情的url
         */
        public static final String GET_HEALTHY_CORE_REPORT_DETAIL_URL = "GET_HEALTHY_CORE_REPORT_DETAIL_URL";

        /**
         * 获取健康险核心理赔工作台的url
         */
        public static final String GET_HEALTHY_CORE_CLAIM_URL = "GET_HEALTHY_CORE_CLAIM_URL";

        /**
         * 获取承保控制台的url
         */
        public static final String GET_HEALTHY_CORE_CHENG_BAO_URL = "GET_HEALTHY_CORE_CHENG_BAO_URL";


        /**
         * 主数据下发当前更新日期
         */
        public static final String MAIN_DATA_UPDATE_TIME = "MAIN_DATA_UPDATE_TIME";
        //新防贫保报案组装下发老防贫保开关
        public static final String REPORT_SEND_OLD_FPB_SWITCH = "report_send_old_fpb_switch";

        /**
         * 调查结论回返老防贫保url
         */
        public static final String SEND_FPB_RESULT_URL = "SEND_FPB_RESULT_URL";

        public static final String SCAN_SUM_URL = "SCAN_SUM_URL";

    }

    /**
     * 存放通用的外部服务ip，同一系统服务，多个接口可以共用ip
     */
    class Url{
        //老长护模块IP地址
        public static final String OLD_LTC_URL="old_ltc_url";
        //老防贫保模块IP地址
        public static final String OLD_FPB_URL="old_fpb_url";

    }

    /**
     *  http请求的路径
     */
    class Path{
        //老长护模块保单校验的地址
        public static final String OLD_LTC_POLICY_VALID_PATH="old_ltc_policy_valid_path";
        //老长护模块查询保单责任的地址
        public static final String OLD_LTC_POLICY_LIABILITY_PATH="old_ltc_policy_liability_path";
        //老防贫保模块立案的地址
        public static final String OLD_FPB_CREATE_SURVEY_PATH="old_fpb_create_survey_path";

    }


    /**
     * 短信类型
     */
    public class Message_Type{
        /**
         * 体检安排
         */
        public static final String PHYSICAL_EXAM="PHYSICAL_EXAM";

        public static final String CASE_DOWN_CODE="CASE_DOWN_CODE"; //case模块下载短信验证码
    }

    /**
     * 通用常量
     */
    class Common {
        //常量
        public static final String VALUE_0 = "0";
        public static final String VALUE_1 = "1";
        public static final String VALUE_2 = "2";
        public static final String VALUE_3 = "3";
        public static final String VALUE_4 = "4";


        //开关
        public static final String ON = "ON";
        public static final String VALUE_SWITCH = "switch";

    }



}
