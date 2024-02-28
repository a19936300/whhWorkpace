package com.binbinxiu.part2

import com.binbinxiu.SpockApplicationTest
import com.binbinxiu.controller.CalculatorController
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

@SpringBootTest
class MathSpec extends Specification{

    @Autowired
    CalculatorController calculatorController;

    def "addition Test"(){
        given:
        def a = 2;
        def b = 3;
        when:
        def result = calculatorController.add(a, b)

        then:
        result == 5
    }


    def "addition Test2"(){
        given:
        def a = 3;
        def b = 3;
        when:
        def result = calculatorController.add(a, b)

        then:
        result == 6
    }
}
