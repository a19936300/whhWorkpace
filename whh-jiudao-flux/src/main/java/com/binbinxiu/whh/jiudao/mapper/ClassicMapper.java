package com.binbinxiu.whh.jiudao.mapper;

import com.binbinxiu.whh.jiudao.entity.Classic;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
public interface ClassicMapper extends ReactiveCrudRepository<Classic,Long> {

    @Query("SELECT * FROM T_CLASSIC ORDER BY INDEX DESC FETCH FIRST 1 ROWS ONLY")
    Flux<Classic> fetchIndexMax();
}
