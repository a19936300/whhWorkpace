package com.binbinxiu.whh.jiudao.demo;


import com.binbinxiu.whh.jiudao.entity.Ming;
import com.binbinxiu.whh.jiudao.mapper.MingR2dbcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;

@DataR2dbcTest
public class MingDemo {

    @Autowired
    private MingR2dbcRepository mingR2dbcRepository;

    @Test
    public void saveDataTest(){
        Flux.range(1,5).map(id -> new Ming(id,"xingming:"+id))
                .flatMap(mingR2dbcRepository::save);

        mingR2dbcRepository.findAll()
                .doOnNext(s -> {
                    System.out.println(s);
                });

    }
}
