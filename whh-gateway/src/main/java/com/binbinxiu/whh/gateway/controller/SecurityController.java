package com.binbinxiu.whh.gateway.controller;

import com.binbinxiu.whh.base.api.vo.UserAuth;
import com.binbinxiu.whh.gateway.constant.ResponseVO;
import com.binbinxiu.whh.gateway.constant.exception.BadCredentialsException;
import com.binbinxiu.whh.gateway.constant.exception.CaptchaException;
import com.binbinxiu.whh.gateway.constant.exception.UsernameNotFoundException;
import com.binbinxiu.whh.gateway.constant.utils.*;
import com.binbinxiu.whh.gateway.dto.UserDto;
import com.binbinxiu.whh.gateway.security.model.JwtModel;
import com.binbinxiu.whh.gateway.security.model.JwtParent;
import com.binbinxiu.whh.gateway.security.model.TokenTime;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * 登陆接口
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/security")
public class SecurityController {

//    @Autowired
//    public RedisTemplate redisTemplate;
//
//    @Autowired
//    private SysConfigFeign sysConfigFeign;
//
//    @Autowired
//    private AuthFeign authFeign;
//
//    @Autowired
//    private FpbFeign fpbFeign;

    @PostMapping("valid")
    public Mono<ResponseEntity<ResponseVO>> valid(@Valid @RequestBody Mono<UserDto> userDto) {
        return Mono.create(sink -> {
            userDto.doOnError(WebExchangeBindException.class, throwable -> {
                String ex = throwable.getFieldErrors().toString();
                ResponseEntity res = ResponseEntity.ok(ResponseVO.errorInstance(ex));
                sink.success(res);
            }).doOnNext(r -> {
                ResponseEntity res = ResponseEntity.ok(ResponseVO.successInstance());
                sink.success(res);
            }).subscribe();
        });
    }


    /**
     * 用户登录接口
     *
     * @param userDto
     * @return
     */
    @PostMapping("login")
    public Mono<ResponseEntity<ResponseVO>> login(@Valid @RequestBody Mono<UserDto> userDto, @RequestHeader("randomKey") String randomKey) {
        return Mono.create(sink -> {
            userDto.doOnError(WebExchangeBindException.class, throwable -> {
                BindingResult result = throwable.getBindingResult();
                FieldError fieldError = result.getFieldError();
                assert fieldError != null;
                log.error(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                String errorMsg = fieldError.getDefaultMessage();
                ResponseEntity res = ResponseEntity.ok(ResponseVO.errorInstance(errorMsg));
                sink.success(res);
            }).doOnNext(r -> {
                //不进行强转编译会报错
                UserDto dto = (UserDto) r;
                log.info(dto.toString());
                ResponseEntity res = null;
                //需要根据用户名查询数据库
                String jwt = "";
                ResponseVO vo = null;
                try {
                    //todo 图形验证码
//                    String value ="true";// sysConfigFeign.getValue("base", "captchaOnOff");
//                    String b = StringUtils.isNotBlank(value) ? value : "true";
//                    boolean captchaOnOff = Boolean.parseBoolean(b);
//                    if (captchaOnOff) {
//                        String captcha = (String) redisTemplate.opsForValue().get(CaptchaController.CAPTCHA_CODE_KEY + dto.getUuid());
//                        //一个验证码 使用一次
//                        redisTemplate.delete(CaptchaController.CAPTCHA_CODE_KEY + dto.getUuid());
//                        if (StringUtils.isBlank(captcha)) {
//                            throw new CaptchaException("验证码已过期！");
//                        }
//                        if (!captcha.equals(dto.getVerifyCode())) {
//                            throw new CaptchaException("验证码错误！");
//                        }
//                    }
                    UserAuth user = new UserAuth();//authFeign.queryByCode(dto.getUnitCode(), dto.getHandlerCode());
                    user.setPassword("G7EeTPnuvSU41T68qsuc/g==");
                    user.setHandlerCode("7176");
                    user.setUnitCode("3010100");
                    user.setId(100L);
                    if (user == null || user.getId() == null) {
                        throw new UsernameNotFoundException("this user not find");
                    }
                    String password = dto.getPassword();
                    if (StringUtils.isNotBlank(randomKey)) {
                        String secretKey = RSAEncryptUtil.decrypt(randomKey, PublicKeyController.privateRsa);
                        password = AESUtil.decrypt(password, secretKey);
                    }
                    // 判断密码是否正确
                    String encryptPwd = SecurityUtilCla.encryptData(password.getBytes());
                    if (!user.getPassword().equals(encryptPwd)) {
                        throw new BadCredentialsException("you have no Credentials");
                    }
                    JwtModel model = JwtModel.builder().id(String.valueOf(user.getId())).userName(user.getHandlerCode()).build();
                    jwt = JwtUtil.createJWT(model, TokenTime.tokenExpTime);
                    /*
                     * 20221021 caozy
                     * 将该 token 存入redis 中 超时时间与 过期时间相同
                     * key JWT_TOKEN_{Id}_{HandleCode}
                     */
//                    String userSwitch = sysConfigFeign.getValue("base", "MANY_OR_SINGLE_USER_SWITCH");
//                    String u = StringUtils.isNotBlank(userSwitch) ? userSwitch : "true";
//                    boolean userOnOff = Boolean.parseBoolean(u);
//                    if (userOnOff) {
//                        String jwtTokenKey = "JWT_TOKEN_" + user.getId() + "_" + user.getHandlerCode();
//                        if (redisTemplate.hasKey(jwtTokenKey)) {
//                            //如果存在 说明 半个小时内登录过
//                            String oldToken = (String) redisTemplate.opsForValue().get(jwtTokenKey);
//                            this.loginOut(oldToken);
//                        }
//                        redisTemplate.opsForValue().set(jwtTokenKey, jwt, TokenTime.tokenExpTime, TimeUnit.MILLISECONDS);
//                    }

                    user.setPassword("");
                    user.setToken(jwt);
                    vo = ResponseVO.successInstance(user);
                    res = ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body(vo);
                } catch (Exception e) {
                    log.error(e.toString());
                    if (e instanceof SecurityException) {
                        res = ResponseEntity.ok(ResponseVO.errorInstance("用户名或密码错误"));
                    } else if (e instanceof CaptchaException) {
                        res = ResponseEntity.ok(ResponseVO.errorInstance(e.getMessage()));
                    } else {
                        res = ResponseEntity.ok(ResponseVO.errorInstance(e.getMessage()));
                    }
                }
                sink.success(res);
            }).subscribe();
        });

    }


    @PostMapping("loginOut")
    public ResponseEntity<ResponseVO> loginOut(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (StringUtils.isBlank(token)) {
            return ResponseEntity.ok(ResponseVO.errorInstance("token为空！"));
        }
        try {
            JwtParent jwtModel = JwtUtil.checkToken(token);
            long diff = jwtModel.getTime() - System.currentTimeMillis();
            String hashKey = Md5Utils.hash(token);
            //计算token剩余时间，存入redis
            //redisTemplate.opsForValue().set(hashKey, token, diff, TimeUnit.MILLISECONDS);
            log.info("用户登出成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            ResponseEntity.ok(ResponseVO.successInstance("登出成功"));
        }

        return ResponseEntity.ok(ResponseVO.successInstance("登出成功"));
    }


}
