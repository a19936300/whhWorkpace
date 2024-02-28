package com.binbinxiu;

import com.binbinxiu.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spock.lang.Specification;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpockApplicationTest extends Specification{

    @Autowired
    private HelloController helloController;

    @Test
    public void HelloTest(){
        String xiaoming = helloController.syaHello("xiaoming");
        assert "hello xiaoming".equals(xiaoming);
    }
}
