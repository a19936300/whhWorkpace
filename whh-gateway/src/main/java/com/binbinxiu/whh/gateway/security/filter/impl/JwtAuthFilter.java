package com.binbinxiu.whh.gateway.security.filter.impl;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.StrUtil;
import com.binbinxiu.whh.gateway.constant.ResponseVO;
import com.binbinxiu.whh.gateway.constant.SysConstant;
import com.binbinxiu.whh.gateway.constant.utils.JwtUtil;
import com.binbinxiu.whh.gateway.constant.utils.Md5Utils;
import com.binbinxiu.whh.gateway.security.filter.AuthChain;
import com.binbinxiu.whh.gateway.security.filter.AuthFilter;
import com.binbinxiu.whh.gateway.security.model.JwtParent;
import com.binbinxiu.whh.gateway.security.model.TokenTime;

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
            }
            exchange.getAttributes().put("jwt", jwtModel);
            Mono mono = chain.doFilter(exchange, gatewayFilterChain, chain);
            return mono;
        } catch (ValidateException e) {
            log.error(e.getMessage());
            return writeResponse(response, HttpStatus.FORBIDDEN, ResponseVO.errorInstance(SysConstant.SYS_CODE.STATUS_OVER, "认证过期"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return writeResponse(response, HttpStatus.FORBIDDEN, ResponseVO.errorInstance(SysConstant.SYS_CODE.TOKEN_ERROR, "认证失败"));
        }
    }

}
