package com.binbinxiu.whh.gateway.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
@Slf4j
public class AuthChain<T> {

    /**
     * 规则过滤器列表，实现Filter接口的过滤器将真正执行对事件的处理
     */
    private Queue<AuthFilter> filters = new ArrayBlockingQueue<>(10);

    public AuthChain(List<AuthFilter> filter){
        log.info("初始化权限过滤链");
        filter.forEach(f->{
            filters.add(f);
        });
    }

    /**
     * 处理事件（alarm）从FilterChain中获取过滤器，进行处理，处理完成之后过滤器会再调用该方法，
     * 继续执行下一个filter.这就需要在每个Filter接口的实现类中最后一句需要回调FilterChain的doFilter方法。
     *
     * @param alarm
     * @param chain
     */
    public Mono<Void> doFilter(T alarm, GatewayFilterChain gatewayFilterChain, AuthChain chain) {
        AuthFilter filter = filters.poll();
        if (filter == null) {
            //return Mono.empty();
            return gatewayFilterChain.filter((ServerWebExchange) alarm);
        }
        //判断当前的链是否需要中断
        return filter.execute(alarm, gatewayFilterChain, chain);
       /* if (b != null){
            return b;
        }
        return null;*/
    }
}
