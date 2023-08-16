package com.binbinxiu.whh.jiudao.service;

import com.binbinxiu.whh.jiudao.entity.Classic;
import reactor.core.publisher.Flux;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
public interface IClassicService {

    /**
     * 获取最新一期期刊
     */
    Flux<Classic> latest();
}
