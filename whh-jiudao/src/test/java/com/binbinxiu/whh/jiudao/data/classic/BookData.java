package com.binbinxiu.whh.jiudao.data.classic;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.binbinxiu.whh.jiudao.entity.Book;
import com.binbinxiu.whh.jiudao.mapper.BookMapper;
import com.binbinxiu.whh.jiudao.util.HTTP;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookData {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void BookDataHostList(){
        String res = HTTP.createHttp().
                requestGet("https://bls.talelin.com/v1/book/hot_list")
                .header("appkey", "XlPyACBYSGylPXnZ")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF XWEB/8259")
                .header("content-type", "application/json")
                .execute()
                .resString();
        JSONArray entries = JSONUtil.parseArray(res);

        Book book = new Book();
        for (int i = 0; i < entries.size(); i++) {
            JSONObject jo = (JSONObject) entries.get(i);
           book.setAuthor(jo.getStr("author"));
           book.setFavNums(jo.getInt("fav_nums"));
           book.setImage(jo.getStr("image"));
           book.setTitle(jo.getStr("title"));
            book.setOldId(jo.getLong("id"));
            book.setId(Long.valueOf(i+1));
            bookMapper.insert(book);
        }
    }
}
