package com.binbinxiu.controller;

import com.binbinxiu.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/add/{a}/{b}")
    public Integer add(@PathVariable("a")Integer a,@PathVariable("b")Integer b){
        return calculatorService.add(a,b);
    }
}
