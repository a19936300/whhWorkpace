package com.binbinxiu.whh.gateway.security.filter.impl;

import cn.hutool.core.util.StrUtil;
import com.binbinxiu.whh.gateway.constant.ResponseVO;
import com.binbinxiu.whh.gateway.constant.SysConstant;
import com.binbinxiu.whh.gateway.constant.utils.JwtUtil;
import com.binbinxiu.whh.gateway.constant.utils.Md5Utils;
import com.binbinxiu.whh.gateway.security.filter.AuthChain;
import com.binbinxiu.whh.gateway.security.filter.AuthFilter;
import com.binbinxiu.whh.gateway.security.model.JwtParent;
import com.binbinxiu.whh.gateway.security.model.TokenTime;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 校验jwt token的有效性
 *
 * @Author wzd
 */
@Order(1)
@Slf4j
@Component
public class JwtAuthFilter implements AuthFilter<ServerWebExchange> {

//    @Autowired
//    public RedisTemplate redisTemplate;

    public static void main(String[] args) {
        JwtParent jwtModel = JwtUtil.checkToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3d3cuY3BpYy5jb20iLCJpZCI6IjcwIiwidXNlck5hbWUiOiIwMDI0ODAiLCJleHAiOjE2NzA0MDMzNzEsImlhdCI6MTY3MDQwMTU3MX0.O7PRQ9rleWG85hAi4Ja5LVItWYltzBcnPeHJSCJ9oew");
        long diff = jwtModel.getTime() - System.currentTimeMillis();
        System.out.println(jwtModel);
        System.out.println(diff);
    }

    @Override
    public Mono<Void> execute(ServerWebExchange exchange, GatewayFilterChain gatewayFilterChain, AuthChain chain) {
        String authToken = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isBlank(authToken)) {
            authToken = exchange.getRequest().getHeaders().getFirst(SysConstant.Header.TOKEN);
        }
        ServerHttpResponse response = exchange.getResponse();
        if (StrUtil.isBlank(authToken)) {
            return writeResponse(response, HttpStatus.FORBIDDEN, ResponseVO.errorInstance(SysConstant.SYS_CODE.TOKEN_ERROR, "登录失效或没有权限操作"));
        }
        String hashKey = Md5Utils.hash(authToken);
       // if (redisTemplate.hasKey(hashKey)) {
        if (false) {
            return writeResponse(response, HttpStatus.FORBIDDEN, ResponseVO.errorInstance(SysConstant.SYS_CODE.STATUS_OVER, "认证过期"));
        }

        try {
            JwtParent jwtModel = JwtUtil.checkToken(authToken);
            long diff = jwtModel.getTime() - System.currentTimeMillis();
            //如果有效期小于5分钟，则不建议继续使用该token
            if (diff < TokenTime.renewTime) {
                String newToken = JwtUtil.createJWT(jwtModel, TokenTime.tokenExpTime);
                response.getHeaders().set(HttpHeaders.AUTHORIZATION, newToken);
//                //20221021 caozy 实现 一个用户只能在一个客户端操作 start
//                String userSwitch = (String) redisTemplate.opsForValue().get("sysConfigCache::moduleType_base_configCode_MANY_OR_SINGLE_USER_SWITCH");
//                if (StringUtils.isBlank(userSwitch)
//                        || (StringUtils.isNotBlank(userSwitch) && ("true".equals(userSwitch) || "on".equals(userSwitch)))) {
//                    //在缓存中找到了开关
//                    //开启状态
//                    if (jwtModel instanceof JwtModel) {
//                        String jwtTokenKey = "JWT_TOKEN_" + ((JwtModel) jwtModel).getId() + "_" + ((JwtModel) jwtModel).getUserName();
//                        if (redisTemplate.hasKey(jwtTokenKey)) {
//                            //如果存在 说明 半个小时内登录过
//                            //计算token剩余时间，存入redis
//                            redisTemplate.opsForValue().set(hashKey, authToken, diff, TimeUnit.MILLISECONDS);
//                        }
//                        redisTemplate.opsForValue().set(jwtTokenKey, newToken, TokenTime.tokenExpTime, TimeUnit.MILLISECONDS);
//                    }                }
//                //20221021 caozy 实现 一个用户只能在一个客户端操作 end

            }
            exchange.getAttributes().put("jwt", jwtModel);
            Mono mono = chain.doFilter(exchange, gatewayFilterChain, chain);
            return mono;
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            return writeResponse(response, HttpStatus.FORBIDDEN, ResponseVO.errorInstance(SysConstant.SYS_CODE.STATUS_OVER, "认证过期"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return writeResponse(response, HttpStatus.FORBIDDEN, ResponseVO.errorInstance(SysConstant.SYS_CODE.TOKEN_ERROR, "认证失败"));
        }
    }

}
