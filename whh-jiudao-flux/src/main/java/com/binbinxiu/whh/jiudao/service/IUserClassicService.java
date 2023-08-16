package com.binbinxiu.whh.jiudao.service;

import com.binbinxiu.whh.jiudao.entity.UserClassic;
import reactor.core.publisher.Mono;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
public interface IUserClassicService {

    Mono<UserClassic> isLikeStatus(Long id, Integer type);
}
