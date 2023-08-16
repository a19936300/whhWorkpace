package com.binbinxiu.whh.jiudao.data.classic;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.binbinxiu.whh.jiudao.entity.Classic;
import com.binbinxiu.whh.jiudao.mapper.ClassicMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;


@DataR2dbcTest
public class ClassicData {

    @Autowired
    private ClassicMapper classicMapper;


    @Test
    public void test1(){
        Classic classic = new Classic();
        classic.setId(1L);
        classic.setContent("内存");
        classic.setPubdate(new Date());
        classic.setCreatedTime(new Date());
        classic.setUpdatedTime(new Date());
        classicMapper.save(classic);

        classicMapper.findAll().doOnNext(s -> {
            System.out.println(s.getContent());
        }).subscribe();
    }
    /**
     * 获取最新一期
     */
    @Test
    public void ClassicDataLastTest() throws Exception {
        CloseableHttpClient hc = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://bls.talelin.com/v1//classic/latest");
        httpGet.setHeader("appkey","XlPyACBYSGylPXnZ");
        httpGet.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF XWEB/8259");
        httpGet.setHeader("content-type","application/json");

        try(CloseableHttpResponse res = hc.execute(httpGet)){
            String resString = EntityUtils.toString(res.getEntity());
            System.out.println(resString);
            JSONObject entries = JSONUtil.parseObj(resString);
            Classic classic = new Classic();
            classic.setId(Long.valueOf(entries.getLong("id")));
            classic.setContent(entries.getStr("content"));
            classic.setFavNums(entries.getInt("fav_nums"));
            classic.setImage(entries.getStr("image"));
            classic.setIndex(entries.getInt("index"));
            //classic.setPubdate(entries.getStr("pubdate"));
            classic.setTitle(entries.getStr("title"));
            classic.setType(entries.getInt("type"));

            System.out.println(classic);
            Mono<Classic> save = classicMapper.save(classic);
            System.out.println(save);
        }
    }

    /**
     * 获取期刊数据
     */
    @Test
    public void ClassicDataTest() throws Exception {
        for (int i = 0; i < 9; i++) {
            try{
                CloseableHttpClient hc = HttpClients.createDefault();

                HttpGet httpGet = new HttpGet("http://bls.talelin.com/v1/classic/"+i+"/previous");
                httpGet.setHeader("appkey","XlPyACBYSGylPXnZ");
                httpGet.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF XWEB/8259");
                httpGet.setHeader("content-type","application/json");

                try(CloseableHttpResponse res = hc.execute(httpGet)){
                    String resString = EntityUtils.toString(res.getEntity());
                    System.out.println(resString);
                    JSONObject entries = JSONUtil.parseObj(resString);
                    Classic classic = new Classic();
                    classic.setOldId(Long.valueOf(entries.getLong("id")));
                    classic.setContent(entries.getStr("content"));
                    classic.setFavNums(entries.getInt("fav_nums"));
                    classic.setImage(entries.getStr("image"));
                    classic.setIndex(entries.getInt("index"));
                    //classic.setPubdate(entries.getStr("pubdate"));
                    classic.setTitle(entries.getStr("title"));
                    classic.setType(entries.getInt("type"));

                    System.out.println(classic);
                    classicMapper.save(classic);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
