package com.binbinxiu.whh.jiudao.controller;

import com.binbinxiu.whh.jiudao.entity.User;
import com.binbinxiu.whh.jiudao.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class User2Controller {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user")
    public Flux<User> test(){
        Flux<User> all = userService.getAll();
        return all;
    }
}
