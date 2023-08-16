package com.binbinxiu.whh.jiudao.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTTP {

    private HttpGet httpGet;
    private CloseableHttpResponse response;

    private HTTP(){}

    public static HTTP createHttp(){
        return new HTTP();
    }

    public HTTP requestGet(String url){
        this.httpGet = new HttpGet(url);
        return this;
    }

    public HTTP header(String name, String value){
        this.httpGet.setHeader(name,value);
        return this;
    }

    public HTTP execute(){
        CloseableHttpClient hc = HttpClients.createDefault();
        try {
            this.response =  hc.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public String resString(){
        String res = "";
        try {
            res = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

}
