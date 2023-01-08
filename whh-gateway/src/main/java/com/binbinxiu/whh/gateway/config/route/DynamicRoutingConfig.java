package com.binbinxiu.whh.gateway.config.route;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * routes:
 * - id: auth
 * uri: lb://auth/
 * predicates:
 * - Path=/auth/**
 * filters:
 * #对应StripPrefixGatewayFilterFactory这个内置的过滤器工厂类，作用是转发请求前 从请求中剥离的路径个数，StripPrefix=0表示不剥离，StripPrefix=1表示剥离一层。
 * - StripPrefix=0
 * # 限流过滤器，使用gateway内置令牌算法
 * - name: RequestRateLimiter  #RequestRateLimiterGatewayFilterFactory
 * args:
 * # 令牌桶每秒填充平均速率,即行等价于允许用户每秒处理多少个请求平均数
 * redis-rate-limiter.replenishRate: 1
 * # 令牌桶的容量，允许在一秒钟内完成的最大请求数
 * redis-rate-limiter.burstCapacity: 2
 * # 用于限流的键的解析器的 Bean 对象的名字。它使用 SpEL 表达式根据#{@beanName}从 Spring 容器中获取 Bean 对象。
 * key-resolver: "#{@ipKeyResolver}"
 *
 * @Author wzd
 */

@Slf4j
@Component
public class DynamicRoutingConfig implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

    private static final List<String> ROUTE_LIST = new ArrayList<>();

    /**
     * 启动的时候读取路由配置
     */
    @PostConstruct
    public void refreshRouting() {
        //路由配置
        URL resource = DynamicRoutingConfig.class.getClassLoader().getResource("route.json");
        String routerConfig = FileUtil.readString(resource, Charset.defaultCharset());


        initRouter(routerConfig);
        log.info("初始化远端路由配置：{}", routerConfig);
       /* configService.addListener(DATA_ID, Group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("路由配置json：{}", configInfo);
                clearRoute();
                List<RouteDefinition> gatewayRouteDefinitions = JSONObject.parseArray(configInfo, RouteDefinition.class);
                for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
                    addRoute(routeDefinition);
                }
                publish();
            }
        });*/
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    private void initRouter(String routerConfig) {
        if (StringUtils.isBlank(routerConfig)) {
            return;
        }

        List<RouteDefinition> gatewayRouteDefinitions = JSONUtil.toList(routerConfig,RouteDefinition.class);
        for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
            log.info(routeDefinition.toString());
            addRoute(routeDefinition);
        }
        log.info("初始路由完成！");
    }

    /**
     * 清除内存中的路由信息
     */
    private void clearRoute() {
        for (String id : ROUTE_LIST) {
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        }
        ROUTE_LIST.clear();
    }

    /**
     * 路由添加
     *
     * @param definition
     */
    private void addRoute(RouteDefinition definition) {
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            ROUTE_LIST.add(definition.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 路由发布
     */
    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }
}
