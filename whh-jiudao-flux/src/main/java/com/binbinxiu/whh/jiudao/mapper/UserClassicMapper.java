package com.binbinxiu.whh.jiudao.mapper;

import com.binbinxiu.whh.jiudao.entity.UserClassic;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
public interface UserClassicMapper extends ReactiveCrudRepository<UserClassic,Long> {

    //@Query("select * FROM T_USER_CLASSIC where ID = ?")
    Mono<UserClassic> queryByIdAndType(Long id, Integer type);
}
