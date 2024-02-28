package com.binbinxiu.controller;

import com.binbinxiu.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hell/{name}")
    public String syaHello(@PathVariable("name")String name){
        return helloService.sayHello(name);
    }
}
