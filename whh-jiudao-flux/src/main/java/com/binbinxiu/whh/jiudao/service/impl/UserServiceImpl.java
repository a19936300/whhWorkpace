package com.binbinxiu.whh.jiudao.service.impl;

import com.binbinxiu.whh.jiudao.entity.User;
import com.binbinxiu.whh.jiudao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@Service
public class UserServiceImpl implements CommandLineRunner {

    @Autowired
    private  UserMapper userMapper;


    public Flux<User> getAll(){
        return  userMapper.findAll();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
