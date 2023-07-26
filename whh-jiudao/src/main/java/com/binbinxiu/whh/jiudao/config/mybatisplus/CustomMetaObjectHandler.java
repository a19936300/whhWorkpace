package com.binbinxiu.whh.jiudao.config.mybatisplus;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自动填充值
 * @Author binbin
 */
@Slf4j
@Component
public class CustomMetaObjectHandler  implements MetaObjectHandler {
    private final String updateUser = "updateUser";

    private final String createUser = "createUser";

    private static Class<?> requestContext;

//    @PostConstruct
//    public void init() throws Exception {
//        try {
//            Class<?> request = Class.forName("com.cpic.ghi.common.utils.RequestContext");
//            requestContext = request;
//            log.info("requestContext:{}", requestContext);
//        } catch (Exception e) {
//            log.error("获取requestContext异常:", e);
//            throw new Exception("获取requestContext异常");
//        }
//    }

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)), metaObject);
        this.setFieldValByName("isDelete", "0", metaObject);
        //Long userId = RequestContext.getUser().getId();// getUserId();
        if (this.getFieldValByName(createUser, metaObject) == null) {
            //this.setFieldValByName(createUser, userId, metaObject);
        }
        if (this.getFieldValByName(updateUser, metaObject) == null) {
            //this.setFieldValByName(updateUser, userId, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        if (this.getFieldValByName(updateUser, metaObject) == null) {
            this.setFieldValByName(updateUser, "admin", metaObject);
        }
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

//    private String getUserId() {
//        try {
//            Method method = requestContext.getMethod("getStringUserId");
//            String id = (String) method.invoke(null);
//            return id;
//        } catch (Exception e) {
//            log.error("获取用户id异常:", e);
//        }
//        return "";
//    }
}
