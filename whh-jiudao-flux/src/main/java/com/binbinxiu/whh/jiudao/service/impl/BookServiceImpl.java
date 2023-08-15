package com.binbinxiu.whh.jiudao.service.impl;

import com.binbinxiu.whh.jiudao.entity.Book;
import com.binbinxiu.whh.jiudao.mapper.BookMapper;
import com.binbinxiu.whh.jiudao.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author binbin
 * @since 2023-08-04
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
