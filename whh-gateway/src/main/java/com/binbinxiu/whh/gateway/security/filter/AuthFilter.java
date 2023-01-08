package com.binbinxiu.whh.gateway.security.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.binbinxiu.whh.gateway.constant.ResponseVO;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public interface AuthFilter<T> {

    /**
     * 权限认证过滤链
     * @param exchange
     * @param gatewayFilterChain
     * @param chain
     * @return
     */
    Mono<Void> execute(T exchange, GatewayFilterChain gatewayFilterChain, AuthChain chain);

    /**
     * 输出response的消息体
     * @param response
     * @param code
     * @param body
     * @return
     */
    default Mono<Void> writeResponse(ServerHttpResponse response, HttpStatus code, ResponseVO body) {
        //response.setStatusCode(code);
        //response.getHeaders().set("code", "403");
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        byte[] bytes = JSONUtil.toJsonStr(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }
}
