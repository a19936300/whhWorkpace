package com.binbinxiu.whh.jiudao.service.impl;

import com.binbinxiu.whh.jiudao.entity.Classic;
import com.binbinxiu.whh.jiudao.mapper.ClassicMapper;
import com.binbinxiu.whh.jiudao.service.IClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
public class ClassicServiceImpl implements IClassicService {

    @Autowired
    private ClassicMapper classicMapper;

    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public Flux<Classic> latest() {
        return classicMapper.fetchIndexMax();
    }

    @Override
    public Flux<Classic> save() {
        Classic classic = new Classic();
        classic.setContent("這是真的");
        classic.setImage("image");
        classic.setId(3);

        classicMapper.save(classic);
        r2dbcEntityTemplate.insert(classic);

        Flux<Classic> tClassic = r2dbcEntityTemplate.select(Classic.class)
                .from("t_classic")
                .all();
        return tClassic;
    }

    @Override
    public Flux<Classic> findAll() {
        return classicMapper.findAll();
    }
}
