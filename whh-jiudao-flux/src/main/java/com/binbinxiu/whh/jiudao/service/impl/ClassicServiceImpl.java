package com.binbinxiu.whh.jiudao.service.impl;

import com.binbinxiu.whh.jiudao.entity.Classic;
import com.binbinxiu.whh.jiudao.mapper.ClassicMapper;
import com.binbinxiu.whh.jiudao.service.IClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@Service
public class ClassicServiceImpl implements IClassicService {

    @Autowired
    private ClassicMapper classicMapper;

    @Override
    public Flux<Classic> latest() {
        return classicMapper.fetchIndexMax();
    }

    @Override
    public Flux<Classic> save() {
        Classic classic = new Classic();
        classic.setContent("這是真的");
        classic.setImage("image");
        classic.setId(2L);
        classicMapper.save(classic);
        return Flux.just(classic);
    }

    @Override
    public Flux<Classic> findAll() {
        return classicMapper.findAll();
    }
}
