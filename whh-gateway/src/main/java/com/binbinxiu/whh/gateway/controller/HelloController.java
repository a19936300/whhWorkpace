package com.binbinxiu.whh.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("health/test")
    public String helloGet(){
        return "whh-gateWay";
    }
}
