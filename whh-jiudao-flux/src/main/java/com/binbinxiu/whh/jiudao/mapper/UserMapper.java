package com.binbinxiu.whh.jiudao.mapper;

import com.binbinxiu.whh.jiudao.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
public interface UserMapper extends ReactiveCrudRepository<User,Long> {

}
