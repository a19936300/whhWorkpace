package com.binbinxiu.whh.gateway.config.gateway;

import com.binbinxiu.whh.gateway.security.filter.AuthChain;
import com.binbinxiu.whh.gateway.security.filter.AuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 1.路由转发
 * 2.识别当前url所属的模块
 * 3.用户的操作鉴权
 * （1）当前链接是否需要鉴权
 * （2）解析jwt token获取用户id及角色id
 * （3）当前链接是否只验证token的正确性
 * （4）当前链接是否根据登陆人id能在数据库中查询
 * 4.记录用户的操作日志，识别出用户的操作类型（crud）,根据操作类型以及耗时发送至mq
 *
 * @author wzd
 * 网关路由拦截
 */
@Slf4j
@Configuration
public class AccessGatewayFilter implements GlobalFilter, Ordered {

    @Autowired
    private List<AuthFilter> authFilters;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain gatewayFilterChain) {
        AuthChain authChain = new AuthChain(authFilters);
        return authChain.doFilter(exchange, gatewayFilterChain, authChain);
    }

    /**
     * 两种查询方法
     * 1.根据url和模块查询所有的角色idA，然后用当前用户所拥有的角色idB去做比较，判断权限
     * 2.根据当前用户查询角色所有idB,根据角色idB查询所有的url,然后匹配url
     * 3.两种方式的查询sql对比？
     * select r.id from role r left join role_user ru on r.id = ru.roleId where ru.userId = userId;
     * select rr.roleId from resource r left join role_resource rr on r.id = rr.resourceId where r.url= url;
     * <p>
     * select res.url from resource res left join role_resource rr on res.id = rr.resourceId where rr.roleId in (roleIds) and res.type='request' and res.module='base';
     *
     * @param url
     * @param moduleType
     */
    private void checkAuth(String url, String moduleType) {
        //List<Long> list = authFeign.queryRoleIdByUrl(url, moduleType);
    }


    /**
     * Ordered, 定义过滤的顺序,
     * getOrder()返回值的大小决定了过滤器执行的优先级，
     * 越小优先级越高
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 2;
        //return -2;
    }

}
