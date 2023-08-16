package com.binbinxiu.whh.jiudao.service.impl;

import com.binbinxiu.whh.jiudao.entity.UserClassic;
import com.binbinxiu.whh.jiudao.mapper.UserClassicMapper;
import com.binbinxiu.whh.jiudao.service.IUserClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@Service
public class UserClassicServiceImpl  implements IUserClassicService {

    @Autowired
    private UserClassicMapper userClassicMapper;

    @Override
    public Mono<UserClassic> isLikeStatus(Long id, Integer type) {
        return null;
    }
}
