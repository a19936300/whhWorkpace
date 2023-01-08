package com.binbinxiu.whh.whhbase.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class HelloController {

    @Autowired
    private RestTemplate  restTemplate;

    @GetMapping("hello")
    public String helloGet(){
        return restTemplate.getForObject("http://whh-gateway/health/test",String.class);
    }

    @GetMapping("hello1")
    public String helloGet1(){
      log.info("33333333333333333333333333333333333333333333333");
      return "succcess";
    }
}
