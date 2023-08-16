package com.binbinxiu.whh.jiudao.mapper;

import com.binbinxiu.whh.jiudao.entity.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author binbin
 * @since 2023-08-04
 */
public interface BookMapper extends ReactiveCrudRepository<Book,Long> {

}
